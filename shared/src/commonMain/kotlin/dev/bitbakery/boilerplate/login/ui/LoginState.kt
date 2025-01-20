package dev.bitbakery.boilerplate.login.ui

data class LoginState(
    val username: String? = null,
    val password: String? = null,
    val error: LoginUiError? = null,
    val success: Boolean? = null,
    val progress: Boolean = false,
)
