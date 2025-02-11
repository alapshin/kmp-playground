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
