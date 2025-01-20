package dev.bitbakery.boilerplate.storage

import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import kotlin.uuid.Uuid

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class FakePathProvider : PathProvider {
    override fun settingsFilePath(): String =
        "/" + PathProvider + "_" + Uuid.random().toString() + PathProvider.SETTINGS_EXTENSION
}
