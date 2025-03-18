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
 * Convert Store's [StoreReadResponse] to a [Result]
 */
inline fun <reified E : Any, reified V : Any> StoreReadResponse<V>.toDataState(): Result<E, V> =
    when (this) {
        is StoreReadResponse.Initial -> Result.Initial
        is StoreReadResponse.Loading -> Result.Loading
        is StoreReadResponse.NoNewData -> Result.NoNewData
        is StoreReadResponse.Data -> Result.Success(this.value)
        is StoreReadResponse.Error -> {
            val error =
                when (this) {
                    is StoreReadResponse.Error.Message -> Error.Unknown(null)
                    is StoreReadResponse.Error.Custom<*> ->
                        if (this.error is ApiError) {
                            Error.Network(this.error as ApiError)
                        } else {
                            Error.Unknown(null)
                        }
                    is StoreReadResponse.Error.Exception ->
                        if (this.error is IOException) {
                            Error.Database(this.error)
                        } else {
                            Error.Unknown(this.error)
                        }
                }
            Result.Failure(error = error as E)
        }
    }
