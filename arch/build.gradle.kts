plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotest)

    id("dev.bitbakery.gradle.compose")
    id("dev.bitbakery.gradle.detekt")
    id("dev.bitbakery.gradle.ktlint")
    id("dev.bitbakery.gradle.android.library")
    id("dev.bitbakery.gradle.kotlin.multiplatform")
}

kotlin {
    @Suppress("UnusedPrivateMember")
    sourceSets {
        all {
            languageSettings.apply {
                progressiveMode = true
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.uuid.ExperimentalUuidApi")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
        commonMain {
            dependencies {
                implementation(libs.kermit)

                implementation(libs.compose.viewmodel)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.molecule)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
            }
        }
    }
}

android {
    namespace = "dev.bitbakery.boilerplate.arch"
}
