package dev.bitbakery.boilerplate.network.inject

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@ContributesTo(AppScope::class)
interface RealEngineComponent : HttpEngineComponent {
    @Provides
    @SingleIn(AppScope::class)
    override fun provideHttpClientEngine(): HttpClientEngine = CIO.create()
}
