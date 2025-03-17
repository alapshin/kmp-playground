package dev.bitbakery.boilerplate.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import coil3.compose.setSingletonImageLoaderFactory
import dev.bitbakery.boilerplate.image.inject.ImageLoaderFactory
import dev.bitbakery.boilerplate.viewmodel.LocalViewModelFactoryOwner
import dev.bitbakery.boilerplate.viewmodel.ViewModelFactoryOwner

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
