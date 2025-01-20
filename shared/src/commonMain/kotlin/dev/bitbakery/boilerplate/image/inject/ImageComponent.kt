package dev.bitbakery.boilerplate.image.inject

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.network.CacheStrategy
import coil3.network.NetworkFetcher
import coil3.network.cachecontrol.CacheControlCacheStrategy
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.util.Logger
import dev.bitbakery.boilerplate.image.KermitCoilLogger
import io.ktor.client.HttpClient
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@ContributesTo(AppScope::class)
interface ImageComponent {
    @OptIn(ExperimentalCoilApi::class)
    @Provides
    @SingleIn(AppScope::class)
    fun provideFetcher(
        httpClient: HttpClient,
        cacheStrategy: CacheStrategy,
    ): NetworkFetcher.Factory =
        KtorNetworkFetcherFactory(
            httpClient = { httpClient },
//            cacheStrategy = { cacheStrategy },
        )

    @Provides
    @SingleIn(AppScope::class)
    fun provideLogger(): Logger = KermitCoilLogger(Logger.Level.Error)

    @Provides
    @SingleIn(AppScope::class)
    fun provideCacheStrategy(): CacheStrategy = CacheControlCacheStrategy()

    @Provides
    @SingleIn(AppScope::class)
    fun provideImageLoaderFactory(
        logger: Logger,
        fetcherFactory: NetworkFetcher.Factory,
    ): ImageLoaderFactory =
        { context: PlatformContext ->
            ImageLoader
                .Builder(context)
                .logger(logger)
                .components {
                    add(fetcherFactory)
                }.build()
        }
}
