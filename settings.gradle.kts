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

include(":feature-home")
project(":feature-home").projectDir = file("../Feature-Home/feature-home")
include(":feature-home-api")
project(":feature-home-api").projectDir = file("../Feature-Home/feature-home-api")

include(":feature-dashboard")
project(":feature-dashboard").projectDir = file("../Feature-Dashboard/feature-dashboard")
include(":feature-dashboard-api")
project(":feature-dashboard-api").projectDir = file("../Feature-Dashboard/feature-dashboard-api")

include(":feature-notifications")
project(":feature-notifications").projectDir = file("../Feature-Notifications/feature-notifications")
include(":feature-notifications-api")
project(":feature-notifications-api").projectDir = file("../Feature-Notifications/feature-notifications-api")