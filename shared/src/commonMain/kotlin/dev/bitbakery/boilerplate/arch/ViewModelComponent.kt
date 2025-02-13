package dev.bitbakery.boilerplate.arch

import androidx.lifecycle.ViewModelProvider
import com.teobaranga.kotlin.inject.viewmodel.runtime.KotlinInjectViewModelFactory
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface ViewModelComponent {
    @Provides
    fun provideViewModelProviderFactory(factory: KotlinInjectViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    fun provideViewModelFactoryOwner(factory: ViewModelProvider.Factory): ViewModelFactoryOwner =
        object : ViewModelFactoryOwner {
            override val viewModelFactory: ViewModelProvider.Factory
                get() = factory
        }
}
