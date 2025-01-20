package dev.bitbakery.boilerplate.home.inject

import dev.bitbakery.boilerplate.base.ViewModelClass
import dev.bitbakery.boilerplate.base.ViewModelInitializer
import dev.bitbakery.boilerplate.home.ui.HomePresenter
import dev.bitbakery.boilerplate.home.ui.HomePresenterImpl
import dev.bitbakery.boilerplate.home.ui.HomeViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface HomeComponent {
    @Provides
    fun HomePresenterImpl.bind(): HomePresenter = this

    @IntoMap
    @Provides
    fun provideHomeViewModel(presenter: HomePresenter): Pair<ViewModelClass, ViewModelInitializer> =
        HomeViewModel::class to { HomeViewModel(presenter) }
}
