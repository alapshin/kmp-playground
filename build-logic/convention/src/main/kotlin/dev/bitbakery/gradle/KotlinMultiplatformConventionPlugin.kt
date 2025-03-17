package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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

            applyDefaultHierarchyTemplate()

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

        configureKotlinMultiplatform()
    }
}
