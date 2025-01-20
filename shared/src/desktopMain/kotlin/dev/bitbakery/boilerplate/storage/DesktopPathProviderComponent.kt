package dev.bitbakery.boilerplate.storage

import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(AppScope::class)
@ContributesTo(AppScope::class)
interface DesktopPathProviderComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun DesktopPathProvider.bind(): PathProvider = this
}
