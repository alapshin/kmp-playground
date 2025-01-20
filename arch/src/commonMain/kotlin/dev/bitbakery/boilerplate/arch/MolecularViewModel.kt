package dev.bitbakery.boilerplate.arch

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Base [ViewModel] class that uses Molecule under the hood to produce state
 */
abstract class MolecularViewModel<Event, State>(
    private val presenter: MolecularPresenter<Event, State>,
) : ViewModel() {
    fun accept(event: Event) {
        if (!events.tryEmit(event)) {
            error("Event buffer overflow")
        }
    }

    val state: StateFlow<State> by lazy(LazyThreadSafetyMode.NONE) {
        viewModelScope.launchMolecule(mode = RecompositionMode.Immediate) {
            state(events)
        }
    }

    // Events have a capacity large enough to handle simultaneous UI events,
    // but small enough to surface issues if they get backed up for some reason.
    private val events = MutableSharedFlow<Event>(extraBufferCapacity = 16)

    @Composable
    private fun state(events: Flow<Event>): State = presenter.state(events)
}
