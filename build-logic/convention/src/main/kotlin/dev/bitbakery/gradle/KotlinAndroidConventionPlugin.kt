package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureKotlinAndroid
import dev.bitbakery.gradle.ext.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.android")
        }

        configureKotlinAndroid()
    }
}
