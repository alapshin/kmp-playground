package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinJvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.jvm")
        }

        configureKotlinJvm()
    }
}
