enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }

    versionCatalogs {
        create("libs") {
            from("com.thoughtworks.ark:VersionCatalog:1.0-SNAPSHOT")
        }
    }
}

include(":plugin")

