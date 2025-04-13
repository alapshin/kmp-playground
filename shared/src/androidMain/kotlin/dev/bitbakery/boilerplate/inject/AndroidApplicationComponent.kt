package dev.bitbakery.boilerplate.inject

import android.content.Context
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory
import dev.bitbakery.boilerplate.viewmodel.ViewModelFactoryOwner
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
abstract class AndroidApplicationComponent(
    @get:Provides protected val context: Context,
) {
    abstract val vmFactoryOwner: ViewModelFactoryOwner

    abstract val imageLoaderFactory: ImageLoaderFactory
}
