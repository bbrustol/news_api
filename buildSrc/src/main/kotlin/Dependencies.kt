import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
        const val FLOW_LAYOUT = "com.google.accompanist:accompanist-flowlayout:${Versions.Google.FLOW_LAYOUT}"
        object Hilt {
            const val ANDROID = "com.google.dagger:hilt-android:${Versions.Google.HILT}"
            const val ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.Google.HILT}"
        }
    }

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.AndroidX.ACTIVITY_COMPOSE}"

        object Compose {
            // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
            const val FOUNDATION = "androidx.compose.foundation:foundation:${Versions.AndroidX.COMPOSE}"
            const val FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:${Versions.AndroidX.COMPOSE}"
            const val RUNTIME = "androidx.compose.runtime:runtime-livedata:${Versions.AndroidX.COMPOSE}"
            const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.AndroidX.MATERIAL3}"

            const val UI ="androidx.compose.ui:ui:${Versions.AndroidX.COMPOSE_UI}"
            const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.LIFECYCLE}"
            const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.AndroidX.LIFECYCLE}"
            const val NAVIGATION = "androidx.navigation:navigation-compose:${ Versions.AndroidX.NAVIGATION}"

            // Tooling support (Previews, etc.)
            const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.AndroidX.COMPOSE_UI}"
            const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.COMPOSE_UI}"
        }
    }

    object Jetbrains {
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
        object Coroutines {
            const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
            const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
            const val PLAY_SERVICES = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.COROUTINES}"
        }
    }

    object Squareup {
        const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.Squareup.RETROFIT}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Squareup.RETROFIT}"
        const val OKHTTP3 = "com.squareup.okhttp3:logging-interceptor:${Versions.Squareup.OKHTTP3}"
    }

    object Others {
        //for image rendering
        const val COIL = "io.coil-kt:coil-compose:${Versions.Others.COIL}"
    }

    object Test {
        object Unit {
            const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
            const val MOCKK = "io.mockk:mockk:${Versions.Test.MOCKK}"
            const val MOCKK_AGENT = "io.mockk:mockk-agent:${Versions.Test.MOCKK}"

            const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Test.CORE_TESTING}"
            const val TURBINE = "app.cash.turbine:turbine:${Versions.Test.TURBINE}"
            const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
        }

        object Integration {
            const val JUNIT = "androidx.test.ext:junit:${Versions.Test.JUNIT_INTEGRATION}"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
            const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:${Versions.Test.ESPRESSO}"
            const val MOCK_WEBSERVER = "com.squareup.okhttp3:mockwebserver:${Versions.Test.MOCK_WEBSERVER}"
            const val MOCKK = "io.mockk:mockk-android:${Versions.Test.MOCKK}"
            const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4:${Versions.Test.COMPOSE_UI_TEST}"
            const val RUNNER = "androidx.test:runner:${Versions.Test.RUNNER}"
            const val ORCHESTRATOR = "androidx.test:orchestrator:${Versions.Test.RUNNER}"
        }
    }

    fun DependencyHandlerScope.commonView() {
        "implementation"(Google.MATERIAL)
        "implementation"(Google.FLOW_LAYOUT)

        "implementation"(AndroidX.ACTIVITY_COMPOSE)
        "implementation"(AndroidX.Compose.FOUNDATION)
        "implementation"(AndroidX.Compose.FOUNDATION_LAYOUT)
        "implementation"(AndroidX.Compose.MATERIAL3)
        "implementation"(AndroidX.Compose.UI)
        "implementation"(AndroidX.Compose.UI_TOOLING_PREVIEW)
        "implementation"(AndroidX.Compose.LIFECYCLE_VIEWMODEL)
        "implementation"(AndroidX.Compose.LIFECYCLE_RUNTIME)
        "implementation"(AndroidX.Compose.RUNTIME)

        "debugImplementation"(AndroidX.Compose.UI_TOOLING)
        "implementation"(Others.COIL)
    }

    fun DependencyHandlerScope.common() {
        "implementation"(AndroidX.APPCOMPAT)
        "implementation"(AndroidX.CORE_KTX)
        "implementation"(Jetbrains.KOTLIN_STDLIB)

        "implementation"(Google.Hilt.ANDROID)
        "kapt"(Google.Hilt.ANDROID_COMPILER)

        "implementation"(Squareup.RETROFIT_MOSHI)

        "implementation"(Jetbrains.Coroutines.CORE)
        "implementation"(Jetbrains.Coroutines.ANDROID)
        "implementation"(Jetbrains.Coroutines.PLAY_SERVICES)
    }

    fun DependencyHandlerScope.commonUnitTest() {
        "testImplementation"(Test.Unit.JUNIT)
        "testImplementation"(Test.Unit.CORE_TESTING)
        "testImplementation"(Test.Unit.TURBINE)
        "testImplementation"(Test.Unit.MOCKK)
        "testImplementation"(Test.Unit.MOCKK_AGENT)
        "testImplementation"(Test.Unit.COROUTINES_TEST)
    }

    fun DependencyHandlerScope.commonIntegrationTest() {
        "androidTestImplementation"(Test.Integration.JUNIT)
        "androidTestImplementation"(Test.Integration.ESPRESSO_CORE)
        "androidTestImplementation"(Test.Integration.ESPRESSO_INTENTS)
        "androidTestImplementation"(Test.Integration.MOCK_WEBSERVER)
        "androidTestImplementation"(Test.Integration.MOCKK)
        "androidTestImplementation"(Test.Integration.COMPOSE_UI_TEST)
        "androidTestImplementation"(Test.Integration.RUNNER)
        "androidTestUtil"(Test.Integration.ORCHESTRATOR)
    }
}