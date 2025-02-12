package dev.bitbakery.boilerplate.start.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import dev.bitbakery.boilerplate.auth.data.LoginManager
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class StartViewModel(
    private val loginManager: LoginManager,
) : MolecularViewModel<StartEvent, StartState>() {
    @Composable
    override fun state(events: Flow<StartEvent>): StartState {
        var success: Boolean by remember { mutableStateOf(false) }
        var progress: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            progress = true
            loginManager
                .authorize()
                .fold(
                    { err -> success = false },
                    { user -> success = user != null },
                )
            progress = false
        }

        return StartState(progress = progress, success = success)
    }
}
