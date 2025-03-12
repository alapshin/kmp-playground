package dev.bitbakery.gradle

import dev.bitbakery.gradle.ext.libs
import dev.bitbakery.gradle.ext.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask

class KtLintConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        with(pluginManager) {
            apply("org.jlleitschuh.gradle.ktlint")
        }

        ktlint {
            version.set(libs.version("ktlint"))
            filter {
                exclude("**/build/**")
                exclude { fte -> fte.file.absolutePath.contains("/build/") }
            }
            reporters {
                reporter(ReporterType.HTML)
                reporter(ReporterType.PLAIN)
            }
        }

        tasks.withType<KtLintCheckTask>().configureEach {
            dependsOn(tasks.named { it == "kspCommonMainKotlinMetadata" })
        }
        tasks.withType<KtLintFormatTask>().configureEach {
            dependsOn(tasks.named { it == "kspCommonMainKotlinMetadata" })
        }

        dependencies {
            "ktlintRuleset"(libs.findLibrary("ktlint-compose").get())
        }
    }
}

internal fun Project.ktlint(action: KtlintExtension.() -> Unit) = extensions.configure<KtlintExtension>(action)

