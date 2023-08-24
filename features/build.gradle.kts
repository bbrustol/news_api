plugins {
    id(Plugins.COMMON_VIEW_LIBRARY)
    id("dagger.hilt.android.plugin")
}
android {
    namespace = "com.bbrustol.features"
}

dependencies {
    implementation(project(Modules.KIT_UI))
    //region domain
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.KIT_SUPPORT))
    //endregion
}
