package dev.bitbakery.boilerplate.start.inject

import dev.bitbakery.boilerplate.auth.data.LoginManager
import dev.bitbakery.boilerplate.base.ViewModelClass
import dev.bitbakery.boilerplate.base.ViewModelInitializer
import dev.bitbakery.boilerplate.start.ui.StartViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface StartComponent {
    @IntoMap
    @Provides
    fun provideViewModelFactory(loginManager: LoginManager): Pair<ViewModelClass, ViewModelInitializer> =
        StartViewModel::class to { StartViewModel(loginManager) }
}
