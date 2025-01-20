package dev.bitbakery.boilerplate.inject

import dev.bitbakery.boilerplate.database.JvmSqlDriverComponent
import dev.bitbakery.boilerplate.network.inject.RealEngineComponent
import dev.bitbakery.boilerplate.storage.DesktopPathProviderComponent
import dev.bitbakery.boilerplate.storage.inject.RealStorageComponent
import io.ktor.client.engine.HttpClientEngine
import me.tatarka.inject.annotations.Component
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent

@Component
@MergeComponent(
    scope = AppScope::class,
    exclude = [
        RealEngineComponent::class,
        RealStorageComponent::class,
        JvmSqlDriverComponent::class,
        DesktopPathProviderComponent::class,
    ],
)
abstract class DesktopTestComponent(
    engine: HttpClientEngine,
) : TestComponent(engine),
    DesktopTestComponentMerged

actual fun createTestComponent(engine: HttpClientEngine): TestComponent = DesktopTestComponent::class.create(engine)
