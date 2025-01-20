package dev.bitbakery.boilerplate.login.ui

sealed interface LoginEvent {
    data class EmailInput(
        val email: String,
    ) : LoginEvent

    data class PasswordInput(
        val password: String,
    ) : LoginEvent

    data object LoginClick : LoginEvent
}
