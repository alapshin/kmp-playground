package dev.bitbakery.gradle.ext

import com.google.devtools.ksp.gradle.KspAATask
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

internal fun Project.configureKotlin() {
    // Configure Java to use our chosen language level. Kotlin will automatically pick this up
    configureJava()
}

internal fun Project.configureKotlinJvm() {
    configureKotlin()

    kotlinJvm {
        compilerOptions {
            apiVersion.set(KotlinVersion.KOTLIN_2_1)
            languageVersion.set(KotlinVersion.KOTLIN_2_1)
            progressiveMode.set(true)
            optIn.add("kotlin.RequiresOptIn")
            optIn.add("kotlin.uuid.ExperimentalUuidApi")
            optIn.add("kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }
}

internal fun Project.configureKotlinMultiplatform() {
    // Configure Java to use our chosen language level. Kotlin will automatically pick this up
    configureJava()

    kotlinMultiplatform {
        compilerOptions {
            apiVersion.set(KotlinVersion.KOTLIN_2_1)
            languageVersion.set(KotlinVersion.KOTLIN_2_1)
            progressiveMode.set(true)
            optIn.add("kotlin.RequiresOptIn")
            optIn.add("kotlin.uuid.ExperimentalUuidApi")
            optIn.add("kotlinx.coroutines.ExperimentalCoroutinesApi")
        }

        sourceSets.named("commonMain").configure {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
    }

    tasks.withType<KspAATask> {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn(tasks.named { it == "kspCommonMainKotlinMetadata" })
        }
    }
    tasks.withType<KotlinCompilationTask<*>> {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn(tasks.named { it == "kspCommonMainKotlinMetadata" })
        }
    }
}

private fun Project.kotlin(action: KotlinBaseExtension.() -> Unit) = extensions.configure<KotlinBaseExtension>(action)
private fun Project.kotlinJvm(action: KotlinJvmExtension.() -> Unit) = extensions.configure<KotlinJvmExtension>(action)
private fun Project.kotlinMultiplatform(action: KotlinMultiplatformExtension.() -> Unit) = extensions.configure<KotlinMultiplatformExtension>(action)
