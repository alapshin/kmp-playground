package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("io.gitlab.arturbosch.detekt")
        }

        detekt {
            parallel = true
            autoCorrect = true
            buildUponDefaultConfig = true
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = JvmTarget.JVM_11.target
            source = project.layout.projectDirectory.asFileTree
            reports {
                md.required.set(true)
                txt.required.set(true)
                xml.required.set(true)
                html.required.set(true)
                sarif.required.set(true)
            }
            if (name != "detekt") {
                dependsOn(tasks.named { it == "kspCommonMainKotlinMetadata" })
            }
            // This exclusion rule is needed for detekt tasks without type resolution
            exclude("**/build/**")
            // This exclusion rule is needed for detekt tasks that are using type resolution
            exclude { fte -> fte.file.absolutePath.contains("/build/") }
        }

        dependencies {
            "detektPlugins"(libs.findLibrary("detekt-rules-arrow").get())
            "detektPlugins"(libs.findLibrary("detekt-rules-compose").get())
        }
    }
}

internal fun Project.detekt(action: DetektExtension.() -> Unit) = extensions.configure<DetektExtension>(action)
