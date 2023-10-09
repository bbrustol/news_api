import Dependencies.common
import Dependencies.commonView
import Dependencies.commonIntegrationTest
import Dependencies.commonUnitTest

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
        kotlinCompilerExtensionVersion = Versions.AndroidX.COMPOSE
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
    common()
    commonView()
    commonUnitTest()
    commonIntegrationTest()
}