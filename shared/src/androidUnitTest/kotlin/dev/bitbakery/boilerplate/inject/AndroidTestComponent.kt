package dev.bitbakery.boilerplate.inject

import dev.bitbakery.boilerplate.database.AndroidSqlDriverComponent
import dev.bitbakery.boilerplate.network.inject.RealEngineComponent
import dev.bitbakery.boilerplate.storage.AndroidPathProviderComponent
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
        AndroidSqlDriverComponent::class,
        AndroidPathProviderComponent::class,
    ],
)
abstract class AndroidTestComponent(
    engine: HttpClientEngine,
) : TestComponent(engine),
    AndroidTestComponentMerged

actual fun createTestComponent(engine: HttpClientEngine): TestComponent = AndroidTestComponent::class.create(engine)
