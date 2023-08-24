import Dependencies.Squareup
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.COMMON_LIBRARY)
}
android {
    namespace = "com.bbrustol.core"

    defaultConfig {
        val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY") ?: ""
        buildConfigField("String", "API_KEY", "\"$key\"")
        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
    }

    flavorDimensions.add("core")
    productFlavors {
        create("bbc-news") {
            dimension = "core"
            buildConfigField("String", "SOURCE", "\"bbc-news\"")
        }

        create("bbc-sport") {
            dimension = "core"
            buildConfigField("String", "SOURCE", "\"bbc-sport\"")
        }
    }

    dependencies {
        implementation(project(Modules.DOMAIN))
        implementation(project(Modules.KIT_SUPPORT))

        implementation (Squareup.RETROFIT)
        implementation (Squareup.OKHTTP3)

        testImplementation(kotlin("test"))
    }
}
