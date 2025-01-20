package dev.bitbakery.boilerplate.coroutines

import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface DispatcherComponent {
    @Provides
    fun dispatcherProvider(provider: AndroidDispatcherProvider): DispatcherProvider = provider
}
