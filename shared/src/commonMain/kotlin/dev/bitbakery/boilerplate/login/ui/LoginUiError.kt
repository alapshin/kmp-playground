package dev.bitbakery.boilerplate.login.ui

import org.jetbrains.compose.resources.StringResource

sealed interface LoginUiError {
    val message: StringResource

    data class Unknown(
        override val message: StringResource,
    ) : LoginUiError

    data class Unauthorized(
        override val message: StringResource,
    ) : LoginUiError
}
