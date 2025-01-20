package dev.bitbakery.boilerplate.network.inject

import io.ktor.client.engine.HttpClientEngine

interface HttpEngineComponent {
    fun provideHttpClientEngine(): HttpClientEngine
}
