package dev.bitbakery.boilerplate.main.view

import androidx.compose.runtime.Composable
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class MainViewModel : MolecularViewModel<MainEvent, MainState>() {
    @Composable
    override fun state(events: Flow<MainEvent>): MainState = MainState(true)
}
