import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

plugins {

    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.App.COMPILE_SDK

    defaultConfig {
        applicationId = "com.bbrustol.newsapi"
        minSdk = Versions.App.MIN_SDK
        targetSdk = Versions.App.TARGET_SDK
        versionCode = Versions.App.VERSION_CODE
        versionName = Versions.App.VERSION_NAME
        multiDexEnabled = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    flavorDimensions.add("core")
    productFlavors {
        create("bbc-news") { dimension = "core" }
        create("bbc-sport") { dimension = "core" }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
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

    packaging {
        resources {
            resources.excludes.add("META-INF/*")
        }
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isReturnDefaultValues = true
        }
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
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
