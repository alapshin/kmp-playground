package dev.bitbakery.boilerplate.storage

import dev.bitbakery.boilerplate.BuildKonfig
import me.tatarka.inject.annotations.Inject

/**
 * [PathProvider] implementation that provides paths conforming to  XDG Base Directory Specification
 */
@Inject
class DesktopPathProvider : PathProvider {
    override fun settingsFilePath(): String {
        val separator = System.getProperty("file.separator")
        return System.getenv("XDG_DATA_HOME") +
            separator + BuildKonfig.APP_NAME +
            separator + PathProvider.SETTINGS_FILENAME + PathProvider.SETTINGS_EXTENSION
    }
}
