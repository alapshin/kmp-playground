package dev.bitbakery.boilerplate.home.ui

import androidx.compose.runtime.Composable
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel : MolecularViewModel<HomeEvent, HomeState>() {
    @Composable
    override fun state(events: Flow<HomeEvent>): HomeState = HomeState()
}
