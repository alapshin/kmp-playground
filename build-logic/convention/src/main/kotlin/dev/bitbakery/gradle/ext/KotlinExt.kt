package dev.bitbakery.gradle.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinJvm() {
    // Configure Java to use our chosen language level. Kotlin will automatically pick this up
    configureJava()

    kotlinJvm {
        compilerOptions {
            optIn.add("kotlin.uuid.ExperimentalUuidApi")
        }
    }
}

internal fun Project.configureKotlinMultiplatform() {
    // Configure Java to use our chosen language level. Kotlin will automatically pick this up
    configureJava()

    kotlinMultiplatform {
        compilerOptions {
            optIn.add("kotlin.RequiresOptIn")
            optIn.add("kotlin.uuid.ExperimentalUuidApi")
            optIn.add("kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }

    tasks.withType<KotlinCompile> {
        if (name != "kspKotlinMetadata") {
            dependsOn(tasks.named { it == "kspKotlinMetadata" })
        }
    }
}

private fun Project.kotlinJvm(action: KotlinJvmExtension.() -> Unit) = extensions.configure<KotlinJvmExtension>(action)
private fun Project.kotlinMultiplatform(action: KotlinMultiplatformExtension.() -> Unit) = extensions.configure<KotlinMultiplatformExtension>(action)
