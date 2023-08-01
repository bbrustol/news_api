plugins {
    id(Plugins.COMMON_VIEW_LIBRARY)
    id("dagger.hilt.android.plugin")
}
android {
    namespace = "com.bbrustol.features"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.UIKIT))
}
