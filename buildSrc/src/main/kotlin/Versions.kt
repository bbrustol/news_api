import org.gradle.api.JavaVersion

object Versions {
    object App {
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
        const val MIN_SDK = 24
        const val TARGET_SDK = 33
        const val COMPILE_SDK = 33
    }

    object Google {
        const val FLOW_LAYOUT = "0.30.1"
        const val HILT = "2.47"
        const val MATERIAL = "1.9.0"
    }

    object AndroidX {
        const val ACTIVITY_COMPOSE = "1.7.2"
        const val APPCOMPAT = "1.6.1"
        const val COMPOSE = "1.4.3"
        const val CONSTRAINT_LAYOUT = "1.0.1"
        const val CORE_KTX = "1.10.1"
        const val LIFECYCLE = "2.6.1"
        const val MATERIAL3 = "1.1.1"
        const val NAVIGATION = "2.6.0"
        const val BIOMETRIC = "1.1.0"
    }

    object Squareup {
        const val RETROFIT = "2.9.0"
        const val OKHTTP3 = "4.11.0"
    }

    object Test {
        const val JUNIT = "4.13.2"
        const val CORE_TESTING = "2.2.0"
        const val JUNIT_INTEGRATION = "1.1.5"
        const val ESPRESSO = "3.5.1"
        const val COMPOSE_UI_TEST = "1.4.3"

        const val MOCKK = "1.13.5"
        const val MOCK_WEBSERVER = "4.11.0"
        const val TURBINE = "1.0.0"

        const val RUNNER = "1.1.0"
    }

    object Others {
        const val COIL = "2.4.0"
    }

    const val COROUTINES = "1.7.3"

    // Make sure to update `buildSrc/build.gradle.kts` when updating this
    const val GRADLE = "8.1.0"
    // Make sure to update `buildSrc/build.gradle.kts` when updating this
    const val KOTLIN = "1.8.10"

    val JAVA = JavaVersion.VERSION_17
}