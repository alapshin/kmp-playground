plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotest)

    id("dev.bitbakery.gradle.detekt")
    id("dev.bitbakery.gradle.ktlint")
    id("dev.bitbakery.gradle.android.library")
    id("dev.bitbakery.gradle.kotlin.multiplatform")
}

dependencies {
    kspAndroid(libs.kotlininject.compiler)
    kspAndroid(libs.kotlininject.anvil.compiler)
    kspAndroidTest(libs.kotlininject.compiler)
    kspAndroidTest(libs.kotlininject.anvil.compiler)

    kspDesktop(libs.kotlininject.compiler)
    kspDesktop(libs.kotlininject.anvil.compiler)
    kspDesktopTest(libs.kotlininject.compiler)
    kspDesktopTest(libs.kotlininject.anvil.compiler)
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
                implementation(libs.kotlininject.runtime)
                implementation(libs.kotlininject.anvil.runtime)
                implementation(libs.kotlininject.anvil.runtime.optional)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.collections)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)
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
    namespace = "dev.bitbakery.boilerplate.core"
}
