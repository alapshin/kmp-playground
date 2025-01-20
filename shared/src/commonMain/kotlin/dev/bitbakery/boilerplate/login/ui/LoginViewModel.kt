package dev.bitbakery.boilerplate.login.ui

import dev.bitbakery.boilerplate.arch.MolecularViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class LoginViewModel(
    presenter: LoginPresenter,
) : MolecularViewModel<LoginEvent, LoginState>(presenter)
