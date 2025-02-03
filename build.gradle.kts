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
}
