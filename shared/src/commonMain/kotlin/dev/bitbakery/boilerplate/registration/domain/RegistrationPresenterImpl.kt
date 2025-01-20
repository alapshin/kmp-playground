package dev.bitbakery.boilerplate.registration.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.bitbakery.boilerplate.auth.data.LoginManager
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class RegistrationPresenterImpl(
    private val loginManager: LoginManager,
) : RegistrationPresenter {
    @Composable
    override fun state(events: Flow<RegistrationMvi.Event>): RegistrationMvi.State {
        var email: String? by remember { mutableStateOf(null) }
        var password: String? by remember { mutableStateOf(null) }
        var success: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(email, password) {
            val e = email
            val p = password
            if (e != null && p != null) {
                loginManager
                    .login(e, p)
                    .onRight {
                        success = true
                    }.onLeft {
                        success = false
                    }
            }
        }

        LaunchedEffect(Unit) {
            events.collect { event ->
                when (event) {
                    is RegistrationMvi.Event.Login -> {
                        email = event.email
                        password = event.password
                    }
                }
            }
        }

        return RegistrationMvi.State(success = success)
    }
}
