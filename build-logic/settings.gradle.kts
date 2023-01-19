@file:Suppress("UnstableApiUsage")

import java.util.Properties

fun readConfig(name: String): String {
    return settings.extensions.extraProperties.properties[name] as String?
        ?: System.getenv(name) ?: ""
}

val buildConfigProperties: Properties
    get() = Properties().apply {
        load(File(rootDir.parent, "buildconfig.properties").reader())
    }

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
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
