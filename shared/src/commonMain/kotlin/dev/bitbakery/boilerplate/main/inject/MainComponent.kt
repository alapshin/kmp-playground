package dev.bitbakery.boilerplate.main.inject

import dev.bitbakery.boilerplate.arch.ViewModelPair
import dev.bitbakery.boilerplate.main.view.MainViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface MainComponent {
    @IntoMap
    @Provides
    fun provideMainViewModelFactory(): ViewModelPair = MainViewModel::class to { MainViewModel() }
}
