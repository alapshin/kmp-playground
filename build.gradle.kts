import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask

plugins {
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)

    alias(libs.plugins.doctor)
    alias(libs.plugins.versions.update)

    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.kotest).apply(false)
    alias(libs.plugins.ktorfit).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)

    alias(libs.plugins.gradle.cachefix) apply (false)
    alias(libs.plugins.android.lint).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.jvm).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.compose).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.parcelize).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.jetbrains.compose).apply(false)

    alias(libs.plugins.openapi.generator).apply(false)
}

doctor {
    // UseG1GC is good enough
    warnWhenNotUsingParallelGC = false
}

versionCatalogUpdate {
    sortByKey.set(false)
    keep {
        keepUnusedVersions.set(true)
    }
}

allprojects {
    // Using task option instead of jvmToolchain property because it allows to use already
    // installed JDK even if JDK version is greater than target version
    tasks.withType<KotlinCompile> {
        if (name != "kspKotlinMetadata") {
            dependsOn(tasks.named { it == "kspKotlinMetadata" })
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        filter {
            isFailOnNoMatchingTests = true
        }
        testLogging {
            showExceptions = true
            showStandardStreams = true
            events =
                setOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                )
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }

    apply(
        plugin =
            rootProject.libs.plugins.detekt
                .get()
                .pluginId,
    )
    detekt {
        autoCorrect = true
        buildUponDefaultConfig = true
    }
    dependencies {
        detektPlugins(rootProject.libs.detekt.arrow)
        detektPlugins(rootProject.libs.detekt.compose)
    }

    tasks.register("detektAll") {
        allprojects {
            this@register.dependsOn(tasks.withType<Detekt>())
        }
    }
    tasks.withType<Detekt>().configureEach {
        jvmTarget = JvmTarget.JVM_11.target
        source = project.layout.projectDirectory.asFileTree
        reports {
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

    apply(
        plugin =
            rootProject.libs.plugins.ktlint
                .get()
                .pluginId,
    )
    dependencies {
        ktlintRuleset(rootProject.libs.ktlint.compose)
    }
    ktlint {
        version.set(
            rootProject.libs.versions.ktlint
                .get(),
        )
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
}
