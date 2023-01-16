pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.toString() == "com.thoughtworks.ark.router") {
                useModule("com.github.TW-Smart-CoE.ARK-Android-Router:com.thoughtworks.ark.router:${requested.version}")
            }
        }
    }

    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "ARK-Android-MainApp"
include(":app")
