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

    dependencies {
        implementation (Squareup.RETROFIT)
        implementation (Squareup.OKHTTP3)

        testImplementation(kotlin("test"))
    }
}
