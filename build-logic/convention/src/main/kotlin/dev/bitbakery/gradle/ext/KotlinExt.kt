package dev.bitbakery.gradle.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlin() {
    // Configure Java to use our chosen language level. Kotlin will automatically pick this up
    configureJava()

    tasks.withType<KotlinCompile> {
        if (name != "kspKotlinMetadata") {
            dependsOn(tasks.named { it == "kspKotlinMetadata" })
        }
    }
}
