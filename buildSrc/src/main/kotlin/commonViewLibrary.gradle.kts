plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.App.COMPILE_SDK
    defaultConfig {
        minSdk = Versions.App.MIN_SDK
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions.add("core")
    productFlavors {
        create("bbc-news") { dimension = "core" }
        create("bbc-sport") { dimension = "core" }
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }

    kotlinOptions {
        jvmTarget = Versions.JAVA.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("androidX-compose").get().toString()
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
        }
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    packaging {
        resources {
            resources.excludes.add("META-INF/*")
        }
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(libs.findBundle("common").get())
    kapt(libs.findLibrary("google-hilt-androidCompiler").get())

    implementation(libs.findBundle("commonView").get())
    debugImplementation(libs.findLibrary("androidX-compose-ui").get())

    testImplementation(libs.findBundle("commonUnitTest").get())

    androidTestImplementation(libs.findBundle("commonIntegrationTest").get())
    androidTestUtil(libs.findLibrary("test-integration-orchestrator").get())
}