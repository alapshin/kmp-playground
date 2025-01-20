package dev.bitbakery.boilerplate.main.inject

import dev.bitbakery.boilerplate.base.ViewModelClass
import dev.bitbakery.boilerplate.base.ViewModelInitializer
import dev.bitbakery.boilerplate.main.view.MainPresenter
import dev.bitbakery.boilerplate.main.view.MainPresenterImpl
import dev.bitbakery.boilerplate.main.view.MainViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface MainComponent {
    @Provides
    fun MainPresenterImpl.bind(): MainPresenter = this

    @IntoMap
    @Provides
    fun provideMainViewModelFactory(presenter: MainPresenter): Pair<ViewModelClass, ViewModelInitializer> =
        MainViewModel::class to { MainViewModel(presenter) }
}
