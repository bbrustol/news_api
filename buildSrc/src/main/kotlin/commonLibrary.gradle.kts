import Dependencies.common
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
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }

    kotlinOptions {
        jvmTarget = Versions.JAVA.toString()
    }

    packaging {
        resources {
            resources.excludes.add("META-INF/*")
        }
    }

}

dependencies {
    common()
    commonUnitTest()
}