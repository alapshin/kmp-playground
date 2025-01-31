import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotest)

    alias(libs.plugins.kotlin.serialization)

    id("dev.bitbakery.gradle.compose")
    id("dev.bitbakery.gradle.detekt")
    id("dev.bitbakery.gradle.ktlint")
    id("dev.bitbakery.gradle.android.library")
    id("dev.bitbakery.gradle.kotlin.multiplatform")

    alias(libs.plugins.ktorfit)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.sqldelight)
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
    compilerOptions {
        optIn.add("coil3.annotation.ExperimentalCoilApi")
    }
    @Suppress("UnusedPrivateMember")
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.arch)
                implementation(projects.core)

                api(compose.ui)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.materialIconsExtended)
                // Needed only for preview.
                implementation(compose.preview)
                implementation(compose.uiTooling)
                implementation(compose.components.resources)

                implementation(libs.compose.viewmodel)
                implementation(libs.compose.navigation)

                implementation(libs.androidx.datastore.preferences)

                implementation(libs.bundles.arrow)
                implementation(libs.bundles.coil)

                implementation(libs.kermit)
                implementation(libs.kotlininject.runtime)
                implementation(libs.kotlininject.anvil.runtime)
                implementation(libs.kotlininject.anvil.runtime.optional)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.collections)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization)

                implementation(libs.ktorfit)
                implementation(libs.bundles.ktor.client)

                implementation(libs.store)
                implementation(libs.sqldelight.coroutines)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotest.assertions.arrow)
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
                implementation(libs.ktor.client.mock)
                implementation(libs.okio.fakefs)

                implementation(libs.sqldelight.jvm)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqldelight.android)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.sqldelight.jvm)
            }
        }

        val desktopTest by getting {
            kotlin.srcDir("buiild/generated/ksp/desktop/desktopTest/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotest.runner.junit5)
            }
        }
    }
}

android {
    namespace = "dev.bitbakery.boilerplate.shared"
}

buildkonfig {
    packageName = "dev.bitbakery.boilerplate"
    defaultConfigs {
        buildConfigField(Type.STRING, "APP_NAME", "boilerplate", const = true)
        buildConfigField(Type.STRING, "API_BASE_URL", "http://10.0.2.2:8080/", const = true)
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("dev.bitbakery.boilerplate.database")
        }
    }
}

compose.resources {
    packageOfResClass = "dev.bitbakery.boilerplate.shared.resources"
}
