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

    implementation(libs.findBundle("common").get())
    kapt(libs.findLibrary("google-hilt-androidCompiler").get())

    testImplementation(libs.findBundle("commonUnitTest").get())
}