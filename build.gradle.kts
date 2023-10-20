//@Suppress("DSL_SCOPE_VIOLATION")
//plugins {
//    id("com.android.application") version "8.1.2" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
//    id("com.google.dagger.hilt.android") version "2.48.1" apply false
////    id(libs.plugins.android.application.get().pluginId)
////    id(libs.plugins.kotlin.android.get().pluginId)
////    alias(libs.plugins.hilt) apply (false)
//}


// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.buildsrc.gradle)
        classpath(libs.buildsrc.kotlin)
        classpath(libs.buildsrc.hilt)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}