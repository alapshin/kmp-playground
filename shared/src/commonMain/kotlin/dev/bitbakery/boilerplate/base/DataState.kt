package dev.bitbakery.boilerplate.base

sealed interface DataState<out E : Any, out V : Any> {
    data object Initial : DataState<Nothing, Nothing>

    data object Loading : DataState<Nothing, Nothing>

    data object NoNewData : DataState<Nothing, Nothing>

    data class Failure<out E : Any>(
        val error: E,
    ) : DataState<E, Nothing>

    data class Success<out V : Any>(
        val value: V,
    ) : DataState<Nothing, V>
}
