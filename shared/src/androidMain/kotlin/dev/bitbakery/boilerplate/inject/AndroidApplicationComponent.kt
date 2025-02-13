package dev.bitbakery.boilerplate.inject

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ForScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
abstract class AndroidApplicationComponent(
    @get:Provides protected val context: Context,
) {
    @ForScope(AppScope::class)
    abstract val vmFactory: ViewModelProvider.Factory
    abstract val vmFactoryOwner: ViewModelFactoryOwner

    abstract val imageLoaderFactory: ImageLoaderFactory
}
