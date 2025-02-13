package dev.bitbakery.boilerplate.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import coil3.compose.setSingletonImageLoaderFactory
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.LocalViewModelFactoryOwner
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.ViewModelFactoryOwner
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory

@Composable
fun App(
    imageLoaderFactory: ImageLoaderFactory,
    viewModelFactoryOwner: ViewModelFactoryOwner,
) {
    setSingletonImageLoaderFactory(imageLoaderFactory)

    CompositionLocalProvider(LocalViewModelFactoryOwner provides viewModelFactoryOwner) {
        BoilerplateApp()
    }
}
