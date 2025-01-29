package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureDetekt
import org.gradle.api.Plugin
import org.gradle.api.Project

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("io.gitlab.arturbosch.detekt")
        }
        configureDetekt()
    }
}
