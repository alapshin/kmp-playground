package dev.bitbakery.boilerplate.inject

import me.tatarka.inject.annotations.Qualifier

@Qualifier
@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.VALUE_PARAMETER,
)
annotation class Named(
    val value: String,
)
