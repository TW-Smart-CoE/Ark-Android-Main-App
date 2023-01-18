@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
    val properties = java.util.Properties().apply {
        load(File(rootDir.parent, "buildconfig.properties").reader())
    }
    val catalogVersion = properties["PLUGIN_CATALOG_VERSION"]

    versionCatalogs {
        create("libs") {
            from("com.thoughtworks.ark:VersionCatalog:${catalogVersion}")
        }
    }
}
