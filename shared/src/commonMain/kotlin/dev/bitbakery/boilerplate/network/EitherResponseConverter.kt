package dev.bitbakery.boilerplate.network

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import me.tatarka.inject.annotations.Inject

/**
 * [SuspendResponseConverter] that supports [Either] as return type
 */
@Inject
class EitherResponseConverter(
    private val typeData: TypeData,
) : Converter.SuspendResponseConverter<HttpResponse, Any> {
    @Suppress("TooGenericExceptionCaught")
    override suspend fun convert(result: KtorfitResult): Any =
        when (result) {
            is KtorfitResult.Failure -> result.throwable.toApiError().left()
            is KtorfitResult.Success ->
                try {
                    result.response
                        .body<Any>(
                            typeData.typeArgs.last().typeInfo,
                        ).right()
                } catch (throwable: Throwable) {
                    throwable.toApiError().left()
                }
        }

    @Inject
    class Factory : Converter.Factory {
        override fun suspendResponseConverter(
            typeData: TypeData,
            ktorfit: Ktorfit,
        ): Converter.SuspendResponseConverter<HttpResponse, *>? =
            if (typeData.typeInfo.type != Either::class) {
                null
            } else {
                EitherResponseConverter(typeData)
            }
    }
}
