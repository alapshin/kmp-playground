rootProject.name = "boilerplate"

val ideaVendor = System.getProperty("idea.vendor.name").orEmpty()
include(
    ":backend",
)
// Disable app modules when building from Intellij IDEA because it requires older AGP
if (!ideaVendor.contains("jetbrains", ignoreCase = true)) {
    include(
        ":core",
        ":shared",
        ":android",
        ":desktop",
    )
}

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex(".*google.*")
                includeGroupByRegex(".*android.*")
            }
        }
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex(".*google.*")
                includeGroupByRegex(".*android.*")
            }
        }
        mavenLocal()
        mavenCentral()
        // Needed for Requery sqlite dependency
        maven(url = "https://www.jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

gradle.beforeProject {
    val localPropertiesFile = rootDir.resolve("local.properties")
    if (localPropertiesFile.exists()) {
        val localProperties = java.util.Properties()
        localProperties.load(localPropertiesFile.inputStream())
        localProperties.forEach { (k, v) ->
            if (k is String) {
                project.extra.set(k, v)
            }
        }
    }
}

// https://docs.gradle.org/7.6/userguide/configuration_cache.html#config_cache:stable
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
