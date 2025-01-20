package dev.bitbakery.boilerplate.storage

import android.content.Context
import me.tatarka.inject.annotations.Inject

/**
 * [PathProvider] implementation that provides paths relative to app's internal file directory
 */
@Inject
class AndroidPathProvider(
    private val context: Context,
) : PathProvider {
    override fun settingsFilePath(): String =
        context.filesDir
            .resolve(
                "${PathProvider.SETTINGS_FILENAME}${PathProvider.SETTINGS_EXTENSION}",
            ).absolutePath
}
