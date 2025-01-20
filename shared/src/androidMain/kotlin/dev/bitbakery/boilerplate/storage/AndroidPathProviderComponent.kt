package dev.bitbakery.boilerplate.storage

import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@ContributesTo(AppScope::class)
interface AndroidPathProviderComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun AndroidPathProvider.bind(): PathProvider = this
}
