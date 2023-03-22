@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import com.thoughtworks.ark.buildlogic.configFeature
import com.thoughtworks.ark.buildlogic.configPrivateMaven
import com.thoughtworks.ark.buildlogic.configVersionCatalog

pluginManagement {
    fun createBuildLogicPath(): String {
        //If it is build by Jenkins, use the project directory to keep BuildLogic
        return if (System.getenv("BUILD_ID").isNullOrEmpty()) {
            "../BuildLogic"
        } else {
            "BuildLogic"
        }
    }

    fun initBuildLogic(buildLogicPath: String) {
        fun execCmd(workPath: String, cmd: String): String {
            val stdout = java.io.ByteArrayOutputStream()
            exec {
                workingDir = file(workPath)
                commandLine(cmd.split(" "))
                standardOutput = stdout
            }
            return stdout.toString().trim()
        }

        if (!file(buildLogicPath).exists()) {
            println("Init build logic...")
            //clone build logic to BuildLogic dir
            val result = execCmd(
                ".",
                "git clone -b main https://github.com/TW-Smart-CoE/BuildLogic.git $buildLogicPath"
            )
            if (result.isNotEmpty()) {
                println(result)
            }
            println("Build logic init success")
        } else {
            println("Update build logic...")
            val result = execCmd(buildLogicPath, "git pull")
            if (result.isNotEmpty()) {
                println(result)
            }
            println("Update build logic success")
        }
    }

    val buildLogicPath = createBuildLogicPath()
    initBuildLogic(buildLogicPath)
    includeBuild(buildLogicPath)

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
        configPrivateMaven(this)
    }

    configVersionCatalog()
}

plugins {
    id("build.setting") apply false
}

rootProject.name = "ARK-Android-MainApp"
include(":app")

configFeature(
    "FeatureHome",
    "https://github.com/TW-Smart-CoE/Ark-Android-Feature-Home.git",
    "feature-home",
    "feature-home-api",
    true
)

configFeature(
    "FeatureDashBoard",
    "https://github.com/TW-Smart-CoE/Ark-Android-Feature-Dashboard.git",
    "feature-dashboard",
    "feature-dashboard-api",
    false
)

configFeature(
    "FeatureNotifications",
    "https://github.com/TW-Smart-CoE/Ark-Android-Feature-Notifications.git",
    "feature-notifications",
    "feature-notifications-api",
    false
)