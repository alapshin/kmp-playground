plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.compose)
    compileOnly(libs.gradle.plugin.compose.compiler)
    compileOnly(libs.gradle.plugin.detekt)
    compileOnly(libs.gradle.plugin.ktlint)
}

tasks {
    validatePlugins {
        failOnWarning = true
        enableStricterValidation = true
    }
}

gradlePlugin {
    plugins {
        register("detekt") {
            id = "dev.bitbakery.gradle.detekt"
            implementationClass = "dev.bitbakery.gradle.DetektConventionPlugin"
        }
        register("ktlint") {
            id = "dev.bitbakery.gradle.ktlint"
            implementationClass = "dev.bitbakery.gradle.KtLintConventionPlugin"
        }
        register("compose-multiplatform") {
            id = "dev.bitbakery.gradle.compose"
            implementationClass = "dev.bitbakery.gradle.ComposeMultiplatformConventionPlugin"
        }
        register("android-library") {
            id = "dev.bitbakery.gradle.android.library"
            implementationClass = "dev.bitbakery.gradle.AndroidLibraryConventionPlugin"
        }
        register("android-application") {
            id = "dev.bitbakery.gradle.android.application"
            implementationClass = "dev.bitbakery.gradle.AndroidApplicationConventionPlugin"
        }
        register("kotlin-android") {
            id = "dev.bitbakery.gradle.kotlin.android"
            implementationClass = "dev.bitbakery.gradle.KotlinAndroidConventionPlugin"
        }
        register("kotlin-jvm") {
            id = "dev.bitbakery.gradle.kotlin.jvm"
            implementationClass = "dev.bitbakery.gradle.KotlinJvmConventionPlugin"
        }
        register("kotlin-multiplatform") {
            id = "dev.bitbakery.gradle.kotlin.multiplatform"
            implementationClass = "dev.bitbakery.gradle.KotlinMultiplatformConventionPlugin"
        }
    }
}
