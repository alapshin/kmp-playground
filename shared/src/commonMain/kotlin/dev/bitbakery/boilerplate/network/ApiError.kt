package dev.bitbakery.boilerplate.network

import io.ktor.client.plugins.ResponseException
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.serialization.ContentConvertException

sealed interface ApiError {
    val cause: Throwable?

    data class HttpError(
        private val statusCode: Int,
        override val cause: Throwable?,
    ) : ApiError

    data class NetworkError(
        override val cause: Throwable?,
    ) : ApiError

    data class SerializationError(
        override val cause: Throwable?,
    ) : ApiError

    data class UnknownError(
        override val cause: Throwable?,
    ) : ApiError
}

fun Throwable.toApiError() =
    when (this) {
        is ResponseException ->
            ApiError.HttpError(
                cause = this,
                statusCode = this.response.status.value,
            )
        is SocketTimeoutException ->
            ApiError.NetworkError(
                cause = this,
            )
        is ContentConvertException ->
            ApiError.SerializationError(
                cause = this,
            )
        else ->
            ApiError.UnknownError(
                cause = this,
            )
    }
