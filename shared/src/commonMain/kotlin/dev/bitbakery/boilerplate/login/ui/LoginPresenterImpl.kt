package dev.bitbakery.boilerplate.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.bitbakery.boilerplate.auth.data.LoginManager
import dev.bitbakery.boilerplate.network.ApiError
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class LoginPresenterImpl(
    private val loginManager: LoginManager,
) : LoginPresenter {
    @Composable
    override fun state(events: Flow<LoginEvent>): LoginState {
        var click: Int by remember { mutableIntStateOf(0) }
        var error: ApiError? by remember { mutableStateOf(null) }
        var username: String? by remember { mutableStateOf(null) }
        var password: String? by remember { mutableStateOf(null) }
        var success: Boolean by remember { mutableStateOf(false) }
        var progress: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(click) {
            val u = username
            val p = password
            if (u != null && p != null) {
                progress = true
                loginManager
                    .login(u, p)
                    .fold(
                        { err ->
                            error = err
                        },
                        { user -> success = true },
                    )
                progress = false
            }
        }

        LaunchedEffect(Unit) {
            events.collect { event ->
                when (event) {
                    is LoginEvent.LoginClick -> {
                        ++click
                    }
                    is LoginEvent.EmailInput -> {
                        username = event.email
                    }
                    is LoginEvent.PasswordInput -> {
                        password = event.password
                    }
                }
            }
        }

        return LoginState(
            username = username,
            password = password,
            success = success,
            progress = progress,
            error = error?.let { it.toUiError() },
        )
    }
}
