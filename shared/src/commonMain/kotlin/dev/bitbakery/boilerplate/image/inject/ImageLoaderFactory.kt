package dev.bitbakery.boilerplate.image.inject

import coil3.ImageLoader
import coil3.PlatformContext

typealias ImageLoaderFactory = (context: PlatformContext) -> ImageLoader
