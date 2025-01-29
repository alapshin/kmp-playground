package dev.bitbakery.gradle.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask

internal fun Project.configureKtLint() {
    extensions.configure<KtlintExtension> {
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
