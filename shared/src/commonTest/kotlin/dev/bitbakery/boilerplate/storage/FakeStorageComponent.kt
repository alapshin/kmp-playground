package dev.bitbakery.boilerplate.storage

import androidx.datastore.core.Storage
import androidx.datastore.core.okio.OkioStorage
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import dev.bitbakery.boilerplate.storage.inject.StorageComponent
import me.tatarka.inject.annotations.Provides
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@ContributesTo(AppScope::class)
interface FakeStorageComponent : StorageComponent {
    @Provides
    @SingleIn(AppScope::class)
    override fun provideStorage(pathProvider: PathProvider): Storage<Preferences> =
        OkioStorage(FakeFileSystem(), PreferencesSerializer) {
            pathProvider.settingsFilePath().toPath()
        }
}
