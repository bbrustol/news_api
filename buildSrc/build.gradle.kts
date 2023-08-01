plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    //need it because of Hilt
    implementation("com.squareup:javapoet:1.13.0")
    implementation(kotlin("script-runtime"))
}