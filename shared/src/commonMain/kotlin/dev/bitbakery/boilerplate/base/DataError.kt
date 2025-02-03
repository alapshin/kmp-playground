package dev.bitbakery.boilerplate.base

import dev.bitbakery.boilerplate.network.ApiError

sealed interface DataError {
    data class UnknownError(
        val throwable: Throwable?,
    ) : DataError

    data class NetworkError(
        val error: ApiError,
    ) : DataError

    data class DatabaseError(
        val throwable: Throwable,
    ) : DataError
}
