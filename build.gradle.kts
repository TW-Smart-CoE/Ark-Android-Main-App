@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    id("build.logic")
    id(libs.plugins.application.get().pluginId) apply false
    id(libs.plugins.library.get().pluginId) apply false
    id(libs.plugins.kotlin.asProvider().get().pluginId) apply false
    id(libs.plugins.kotlin.jvm.get().pluginId) apply false
    id(libs.plugins.kotlin.parcelize.get().pluginId) apply false
    id(libs.plugins.kapt.get().pluginId) apply false

    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.router) apply false
}

apply(from = "config/jacoco/project.kts")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}