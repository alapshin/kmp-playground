package dev.bitbakery.boilerplate.storage.inject

import androidx.datastore.core.Storage
import androidx.datastore.preferences.core.Preferences
import dev.bitbakery.boilerplate.storage.PathProvider

interface StorageComponent {
    fun provideStorage(pathProvider: PathProvider): Storage<Preferences>
}
