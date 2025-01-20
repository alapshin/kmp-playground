package dev.bitbakery.boilerplate.main.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.bitbakery.boilerplate.auth.data.LoginManager
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class MainPresenterImpl(
    private val loginManager: LoginManager,
) : MainPresenter {
    @Composable
    override fun state(events: Flow<MainEvent>): MainState {
        val isLogged by loginManager.isLoggedIn().collectAsState(false)

        LaunchedEffect(Unit) {
            events.collect { event ->
                when (event) {
                    is MainEvent.Logout -> {
                        loginManager.logout()
                    }
                }
            }
        }

        return MainState(isUserLoggedIn = isLogged)
    }
}
