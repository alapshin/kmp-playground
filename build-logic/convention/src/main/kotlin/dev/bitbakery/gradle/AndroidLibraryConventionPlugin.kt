package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.gradle.android.cache-fix")
        }

        configureAndroid()
    }
}
