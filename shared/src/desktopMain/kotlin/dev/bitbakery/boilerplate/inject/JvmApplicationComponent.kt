package dev.bitbakery.boilerplate.inject

import androidx.lifecycle.ViewModelProvider
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
abstract class JvmApplicationComponent {
    abstract val vmFactory: ViewModelProvider.Factory
    abstract val vmFactoryOwner: ViewModelFactoryOwner

    abstract val imageLoaderFactory: ImageLoaderFactory
}
