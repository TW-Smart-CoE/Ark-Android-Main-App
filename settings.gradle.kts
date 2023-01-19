@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import java.util.Properties

fun readConfig(name: String): String {
    return settings.extensions.extraProperties.properties[name] as String?
        ?: System.getenv(name) ?: ""
}

val buildConfigProperties: Properties
    get() = Properties().apply {
        load(File(rootDir, "buildconfig.properties").reader())
    }

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
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal()
        maven {
            url = uri(readConfig("MAVEN_REPO"))
            isAllowInsecureProtocol = true
            credentials {
                username = readConfig("MAVEN_USER")
                password = readConfig(("MAVEN_PWD"))
            }
        }
    }

    val catalogVersion = buildConfigProperties["PLUGIN_CATALOG_VERSION"]

    versionCatalogs {
        create("libs") {
            from("com.thoughtworks.ark:versioncatalog:${catalogVersion}")
        }
    }
}

rootProject.name = "ARK-Android-MainApp"
include(":app")