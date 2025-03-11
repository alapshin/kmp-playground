package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureAndroid
import dev.bitbakery.gradle.ext.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
            apply("org.gradle.android.cache-fix")
        }


        configureAndroid()
        configureKotlinAndroid()
    }
}

