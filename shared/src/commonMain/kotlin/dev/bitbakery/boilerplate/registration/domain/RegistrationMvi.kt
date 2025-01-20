package dev.bitbakery.boilerplate.registration.domain

class RegistrationMvi private constructor() {
    data class State(
        val value: Int = 0,
        val error: Error? = null,
        val progress: Boolean = false,
        val success: Boolean? = null,
    )

    sealed interface Error

    sealed interface Event {
        data class Login(
            val email: String,
            val password: String,
        ) : Event
    }
}
