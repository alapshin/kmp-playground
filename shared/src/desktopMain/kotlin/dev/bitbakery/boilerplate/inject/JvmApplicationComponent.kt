package dev.bitbakery.boilerplate.inject

import dev.bitbakery.boilerplate.base.ViewModelFactory
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
abstract class JvmApplicationComponent {
    abstract val viewModelFactory: ViewModelFactory
    abstract val imageLoaderFactory: ImageLoaderFactory
}
