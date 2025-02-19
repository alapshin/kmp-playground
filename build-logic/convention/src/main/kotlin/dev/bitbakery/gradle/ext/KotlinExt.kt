package dev.bitbakery.gradle.ext

import com.google.devtools.ksp.gradle.KspAATask
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
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
    val kspGeneratedDir = "${layout.buildDirectory.get()}/generated/ksp"

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
            kotlin.srcDir("$kspGeneratedDir/metadata/commonMain/kotlin")
        }

        sourceSets.named("androidMain").configure {
            project.android {
                sourceSets.named("debug") {
                    kotlin.srcDir("$kspGeneratedDir/android/androidDebug/kotlin")
                }
                sourceSets.named("release") {
                    kotlin.srcDir("$kspGeneratedDir/android/androidRelease/kotlin")
                }
            }
        }

//        sourceSets.named("desktopMain").configure {
//            kotlin.srcDir("$kspGeneratedDir/desktop/desktopMain/kotlin")
//        }
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
