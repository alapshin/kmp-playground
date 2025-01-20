package dev.bitbakery.boilerplate.login.ui

import dev.bitbakery.boilerplate.login.ui.LoginUiError.Unauthorized
import dev.bitbakery.boilerplate.login.ui.LoginUiError.Unknown
import dev.bitbakery.boilerplate.network.ApiError
import dev.bitbakery.boilerplate.shared.resources.Res
import dev.bitbakery.boilerplate.shared.resources.login_unauthorized_error_message
import dev.bitbakery.boilerplate.shared.resources.login_unknown_error_message

fun ApiError.toUiError(): LoginUiError =
    when (this) {
        is ApiError.HttpError ->
            Unauthorized(
                Res.string.login_unauthorized_error_message,
            )
        is ApiError.NetworkError ->
            Unknown(
                Res.string.login_unknown_error_message,
            )
        is ApiError.SerializationError ->
            Unknown(
                Res.string.login_unknown_error_message,
            )
        is ApiError.UnknownError ->
            Unknown(
                Res.string.login_unknown_error_message,
            )
    }
