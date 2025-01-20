package dev.bitbakery.boilerplate.home.ui

import dev.bitbakery.boilerplate.arch.MolecularViewModel

class HomeViewModel(
    presenter: HomePresenter,
) : MolecularViewModel<HomeEvent, HomeState>(presenter)
