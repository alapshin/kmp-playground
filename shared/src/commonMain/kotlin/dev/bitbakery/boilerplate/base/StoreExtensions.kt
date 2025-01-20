package dev.bitbakery.boilerplate.base

import arrow.core.Either
import org.mobilenativefoundation.store.store5.FetcherResult
import org.mobilenativefoundation.store.store5.StoreReadResponse

inline fun <reified Error : Any, reified Value : Any> Either<Error, Value>.toFetcherResult(): FetcherResult<Value> =
    when (this) {
        is Either.Left -> FetcherResult.Error.Custom(this.value)
        is Either.Right -> FetcherResult.Data(this.value)
    }

inline fun <reified Error : Any, reified Value : Any> StoreReadResponse<Value>.toDataState(): DataState<Error, Value> =
    when (this) {
        is StoreReadResponse.Initial -> DataState.Initial
        is StoreReadResponse.Loading -> DataState.Loading
        is StoreReadResponse.Data -> DataState.Value(this.value)
        is StoreReadResponse.NoNewData -> DataState.Empty
        is StoreReadResponse.Error ->
            when (this) {
                is StoreReadResponse.Error.Message -> DataState.Initial
                is StoreReadResponse.Error.Exception -> DataState.Initial
                is StoreReadResponse.Error.Custom<*> -> DataState.Error(this.error as Error)
            }
    }
