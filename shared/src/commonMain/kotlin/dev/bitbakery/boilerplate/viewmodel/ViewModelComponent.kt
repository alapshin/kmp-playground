package dev.bitbakery.boilerplate.viewmodel

import androidx.lifecycle.ViewModelProvider
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface ViewModelComponent {
    @Provides
    fun provideViewModelFactoryOwner(factory: ViewModelProvider.Factory): ViewModelFactoryOwner =
        object : ViewModelFactoryOwner {
            override val viewModelFactory: ViewModelProvider.Factory
                get() = factory
        }

    @Provides
    fun provideViewModelProviderFactory(factory: ViewModelProviderInjectFactory): ViewModelProvider.Factory = factory
}
