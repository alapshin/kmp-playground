package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            jvm("desktop")
            if (pluginManager.hasPlugin("com.android.library")) {
                androidTarget()
            }

            targets.configureEach {
                compilations.configureEach {
                    compileTaskProvider.configure {
                        compilerOptions {
                            allWarningsAsErrors.set(true)
                            freeCompilerArgs.add("-Xexpect-actual-classes")
                        }
                    }
                }
            }
        }

        configureKotlin()
    }
}

private fun Project.addKspDependencyForAllTargets(configurationNameSuffix: String, dependencyNotation: Any) {
    val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
    dependencies {
        kmpExtension.targets
            .asSequence()
            .filter { target ->
                // Don't add KSP for common target, only final platforms
                target.platformType != KotlinPlatformType.common
            }
            .forEach { target ->
                add(
                    "ksp${target.targetName.capitalized()}$configurationNameSuffix",
                    dependencyNotation,
                )
            }
    }
}

fun Project.addKspDependencyForAllTargets(dependencyNotation: Any) {
    addKspDependencyForAllTargets("", dependencyNotation)
}

fun Project.addKspTestDependencyForAllTargets(dependencyNotation: Any) {
    addKspDependencyForAllTargets("Test", dependencyNotation)
}

