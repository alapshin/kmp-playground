package dev.bitbakery.boilerplate.start.ui

import dev.bitbakery.boilerplate.arch.MolecularViewModel
import me.tatarka.inject.annotations.Inject

@Inject
class StartViewModel(
    presenter: StartPresenter,
) : MolecularViewModel<StartEvent, StartState>(presenter)
