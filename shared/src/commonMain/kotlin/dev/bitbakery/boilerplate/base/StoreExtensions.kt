package dev.bitbakery.boilerplate.base

import arrow.core.Either
import dev.bitbakery.boilerplate.network.ApiError
import kotlinx.io.IOException
import org.mobilenativefoundation.store.store5.FetcherResult
import org.mobilenativefoundation.store.store5.StoreReadResponse

/**
 * Convert Arrow's [Either] to Store's [FetcherResult]
 *
 * @param E the error type
 * @param V the value type
 */
inline fun <reified E : Any, reified V : Any> Either<E, V>.toFetcherResult(): FetcherResult<V> =
    when (this) {
        is Either.Left ->
            FetcherResult.Error.Custom(
                this.value,
            )
        is Either.Right -> FetcherResult.Data(this.value)
    }

/**
 * Convert Store's [StoreReadResponse] to a [DataState]
 */
inline fun <reified E : Any, reified V : Any> StoreReadResponse<V>.toDataState(): DataState<E, V> =
    when (this) {
        is StoreReadResponse.Initial -> DataState.Initial
        is StoreReadResponse.Loading -> DataState.Loading
        is StoreReadResponse.NoNewData -> DataState.NoNewData
        is StoreReadResponse.Data -> DataState.Success(this.value)
        is StoreReadResponse.Error -> {
            val error =
                when (this) {
                    is StoreReadResponse.Error.Message -> DataError.UnknownError(null)
                    is StoreReadResponse.Error.Custom<*> ->
                        if (this.error is ApiError) {
                            DataError.NetworkError(this.error as ApiError)
                        } else {
                            DataError.UnknownError(null)
                        }
                    is StoreReadResponse.Error.Exception ->
                        if (this.error is IOException) {
                            DataError.DatabaseError(this.error)
                        } else {
                            DataError.UnknownError(this.error)
                        }
                }
            DataState.Failure(error = error as E)
        }
    }
