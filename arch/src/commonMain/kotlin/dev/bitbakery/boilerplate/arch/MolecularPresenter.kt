package dev.bitbakery.boilerplate.arch

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

fun interface MolecularPresenter<Event, State> {
    @Composable
    fun state(events: Flow<Event>): State
}
