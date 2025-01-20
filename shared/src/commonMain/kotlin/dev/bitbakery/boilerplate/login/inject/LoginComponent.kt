package dev.bitbakery.boilerplate.login.inject

import dev.bitbakery.boilerplate.base.ViewModelClass
import dev.bitbakery.boilerplate.base.ViewModelInitializer
import dev.bitbakery.boilerplate.login.ui.LoginPresenter
import dev.bitbakery.boilerplate.login.ui.LoginPresenterImpl
import dev.bitbakery.boilerplate.login.ui.LoginViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface LoginComponent {
    @Provides
    fun LoginPresenterImpl.bind(): LoginPresenter = this

    @IntoMap
    @Provides
    fun provideLoginViewModel(presenter: LoginPresenter): Pair<ViewModelClass, ViewModelInitializer> =
        LoginViewModel::class to { LoginViewModel(presenter) }
}
