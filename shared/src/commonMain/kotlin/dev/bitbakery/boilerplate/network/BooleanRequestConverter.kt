package dev.bitbakery.boilerplate.network

import de.jensklingenberg.ktorfit.converter.Converter
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

/**
 * [RequestParameterConverter] that converts boolean parameters to integer
 */
@Inject
class BooleanRequestConverter : Converter.RequestParameterConverter {
    override fun convert(data: Any): Any = if (data as Boolean) 1 else 0

    @Inject
    class Factory : Converter.Factory {
        override fun requestParameterConverter(
            parameterType: KClass<*>,
            requestType: KClass<*>,
        ): Converter.RequestParameterConverter? {
            return if (requestType != Int::class || parameterType != Boolean::class) {
                null
            } else {
                return BooleanRequestConverter()
            }
        }
    }
}
