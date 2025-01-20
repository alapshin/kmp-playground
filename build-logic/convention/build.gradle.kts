plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.compose)
    compileOnly(libs.gradle.plugin.compose.compiler)
}

gradlePlugin {
    plugins {
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
        register("kotlin-multiplatfomr") {
            id = "dev.bitbakery.gradle.kotlin.multiplatform"
            implementationClass = "dev.bitbakery.gradle.KotlinMultiplatformConventionPlugin"
        }
    }
}
