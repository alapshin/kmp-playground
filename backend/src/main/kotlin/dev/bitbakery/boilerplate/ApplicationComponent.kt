package dev.bitbakery.boilerplate

import dev.bitbakery.boilerplate.post.route.PostRouting
import io.ktor.server.application.Application
import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
abstract class ApplicationComponent(
    @get:Provides protected val application: Application,
) {
    abstract val postRouting: PostRouting

    @Provides
    @SingleIn(AppScope::class)
    fun applicationConfig(): ApplicationConfig = application.environment.config
}
