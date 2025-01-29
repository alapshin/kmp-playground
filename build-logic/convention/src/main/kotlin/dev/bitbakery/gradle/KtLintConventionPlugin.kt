package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureKtLint
import org.gradle.api.Plugin
import org.gradle.api.Project

class KtLintConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        with(pluginManager) {
            apply("org.jlleitschuh.gradle.ktlint")
        }
        configureKtLint()
    }
}
