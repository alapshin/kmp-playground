package dev.bitbakery.boilerplate.base

import dev.bitbakery.boilerplate.network.ApiError

sealed interface Error {
    data class Unknown(
        val throwable: Throwable?,
    ) : Error

    data class Network(
        val error: ApiError,
    ) : Error

    data class Database(
        val throwable: Throwable,
    ) : Error
}
