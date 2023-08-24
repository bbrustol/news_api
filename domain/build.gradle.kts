plugins {
    id(Plugins.COMMON_LIBRARY)
}
android {
    namespace = "com.bbrustol.domain"

    flavorDimensions.add("core")
    productFlavors {
        create("bbc-news") { dimension = "core" }
        create("bbc-sport") { dimension = "core" }
    }

}

dependencies {
    implementation(project(Modules.KIT_SUPPORT))
}
