package dev.bitbakery.boilerplate.main.view

import dev.bitbakery.boilerplate.arch.MolecularViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class MainViewModel(
    presenter: MainPresenter,
) : MolecularViewModel<MainEvent, MainState>(presenter)
