plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `version-catalog`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.buildsrc.gradle)
    implementation(libs.buildsrc.kotlin)

    //need it because of Hilt
    implementation(libs.buildsrc.javapoet)
    implementation(kotlin("script-runtime"))
}