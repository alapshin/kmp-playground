package dev.bitbakery.boilerplate.base

sealed interface Result<out E : Any, out V : Any> {
    data object Initial : Result<Nothing, Nothing>

    data object Loading : Result<Nothing, Nothing>

    data object NoNewData : Result<Nothing, Nothing>

    data class Failure<out E : Any>(val error: E) : Result<E, Nothing>

    data class Success<out V : Any>(val value: V) : Result<Nothing, V>
}
