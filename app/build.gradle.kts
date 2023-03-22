@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import com.thoughtworks.ark.buildlogic.androidApplication
import com.thoughtworks.ark.buildlogic.autoImplementation
import com.thoughtworks.ark.buildlogic.enableCompose

plugins {
    id(libs.plugins.application.get().pluginId)
    id(libs.plugins.kotlin.asProvider().get().pluginId)
    id(libs.plugins.kapt.get().pluginId)

    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.router)
}

apply(from = "../config/jacoco/modules.kts")

androidApplication {
    namespace = "com.thoughtworks.ark.app"

    defaultConfig {
        applicationId = "com.thoughtworks.ark.app"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    enableCompose()

    kapt {
        correctErrorTypes = true
    }

    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    autoImplementation("feature-home", "1.0.0")
    autoImplementation("feature-dashboard", "1.0.0")
    autoImplementation("feature-notifications", "1.0.0")

    autoImplementation("foundation", "1.0.0")

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.router)
    kapt(libs.router.compiler)

    implementation(libs.bundles.coil)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.truth)

    detektPlugins(libs.detekt.formatting)
}