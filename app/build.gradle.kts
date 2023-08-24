plugins {
    id(Plugins.APPLICATION)
    id("dagger.hilt.android.plugin")
}
android {
    namespace = "com.bbrustol.newsapi"

    hilt {
        enableExperimentalClasspathAggregation = false
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.FEATURES))
    implementation(project(Modules.KIT_UI))
    implementation(project(Modules.KIT_SUPPORT))
    implementation(project(Modules.CORE))
}