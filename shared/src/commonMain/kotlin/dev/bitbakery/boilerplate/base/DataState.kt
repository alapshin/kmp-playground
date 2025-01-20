package dev.bitbakery.boilerplate.base

sealed interface DataState<out Error : Any, out Value : Any> {
    data object Initial : DataState<Nothing, Nothing>

    data class Error<out Error : Any>(
        val error: Error,
    ) : DataState<Error, Nothing>

    data class Value<out Value : Any>(
        val value: Value,
    ) : DataState<Nothing, Value>

    data object Empty : DataState<Nothing, Nothing>

    data object Loading : DataState<Nothing, Nothing>
}
