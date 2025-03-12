import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("dev.bitbakery.gradle.detekt")
    id("dev.bitbakery.gradle.ktlint")
    id("dev.bitbakery.gradle.compose")
    id("dev.bitbakery.gradle.android.application")
    id("dev.bitbakery.gradle.kotlin.android")
}

android {
    namespace = "dev.bitbakery.boilerplate.android"

    defaultConfig {
        applicationId = "dev.bitbakery.boilerplate"
        versionCode = 1
        versionName = "0.0.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    applicationVariants.all {
        outputs.filterIsInstance<BaseVariantOutputImpl>().forEach { output ->
            output.outputFileName =
                if (flavorName.isEmpty()) {
                    "${rootProject.name}-${buildType.name}-$versionName.apk"
                } else {
                    "${rootProject.name}-$flavorName-${buildType.name}-$versionName.apk"
                }
        }
    }
}

dependencies {
    implementation(projects.shared)
}
