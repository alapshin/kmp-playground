package dev.bitbakery.gradle.ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun VersionCatalog.version(alias: String): String {
    return findVersion(alias)
        .get()
        .requiredVersion
}

internal val Project.libs: VersionCatalog
  get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
