package dev.bitbakery.boilerplate.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import coil3.compose.setSingletonImageLoaderFactory
import dev.bitbakery.boilerplate.base.LocalViewModelFactory
import dev.bitbakery.boilerplate.base.ViewModelFactory
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory

@Composable
fun App(
    viewModelFactory: ViewModelFactory,
    imageLoaderFactory: ImageLoaderFactory,
) {
    setSingletonImageLoaderFactory(imageLoaderFactory)

    CompositionLocalProvider(LocalViewModelFactory provides viewModelFactory) {
        BoilerplateApp()
    }
}
