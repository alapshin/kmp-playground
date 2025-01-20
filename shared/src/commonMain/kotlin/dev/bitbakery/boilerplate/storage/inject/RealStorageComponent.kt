package dev.bitbakery.boilerplate.storage.inject

import androidx.datastore.core.Storage
import androidx.datastore.core.okio.OkioStorage
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import dev.bitbakery.boilerplate.storage.PathProvider
import me.tatarka.inject.annotations.Provides
import okio.FileSystem
import okio.Path.Companion.toPath
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@ContributesTo(AppScope::class)
interface RealStorageComponent : StorageComponent {
    @Provides
    @SingleIn(AppScope::class)
    override fun provideStorage(pathProvider: PathProvider): Storage<Preferences> =
        OkioStorage(FileSystem.SYSTEM, PreferencesSerializer) {
            pathProvider.settingsFilePath().toPath()
        }
}
