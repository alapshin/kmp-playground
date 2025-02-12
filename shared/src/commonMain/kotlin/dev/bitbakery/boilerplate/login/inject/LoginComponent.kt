package dev.bitbakery.boilerplate.login.inject

import dev.bitbakery.boilerplate.auth.data.LoginManager
import dev.bitbakery.boilerplate.base.ViewModelClass
import dev.bitbakery.boilerplate.base.ViewModelInitializer
import dev.bitbakery.boilerplate.login.ui.LoginViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface LoginComponent {
    @IntoMap
    @Provides
    fun provideLoginViewModel(loginManager: LoginManager): Pair<ViewModelClass, ViewModelInitializer> =
        LoginViewModel::class to { LoginViewModel(loginManager) }
}
