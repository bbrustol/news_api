plugins {
    id(Plugins.COMMON_LIBRARY)
}
android {
    namespace = "com.bbrustol.kit.support"

    flavorDimensions.add("core")
    productFlavors {
        create("bbc-news") { dimension = "core" }
        create("bbc-sport") { dimension = "core" }
    }

}
