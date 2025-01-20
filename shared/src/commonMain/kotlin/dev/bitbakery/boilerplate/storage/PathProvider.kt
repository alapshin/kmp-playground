package dev.bitbakery.boilerplate.storage

/**
 * Interface for querying paths from a system
 */
interface PathProvider {
    fun settingsFilePath(): String

    companion object {
        const val SETTINGS_FILENAME = "settings"
        const val SETTINGS_EXTENSION = ".preferences_pb"
    }
}
