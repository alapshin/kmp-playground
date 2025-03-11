package dev.bitbakery.gradle.ext

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

internal fun Project.configureKotlin() {
    // Configure Java to use our chosen language level. Kotlin will automatically pick this up
    configureJava()
}

internal fun Project.configureKotlinAndroid() {
    configureKotlin()

    kotlinAndroid {
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

    // Needed to work around incorrect task dependencies for ksp tasks similar to
    //  Reason: Task ':shared:kspDebugKotlinAndroid' uses this output of task ':shared:kspCommonMainKotlinMetadata'
    //  without declaring an explicit or implicit dependency.
    tasks.named { name -> name.startsWith("ksp") && name != "kspCommonMainKotlinMetadata"  }.configureEach {
        dependsOn(tasks.named { it == "kspCommonMainKotlinMetadata" })
    }
}
