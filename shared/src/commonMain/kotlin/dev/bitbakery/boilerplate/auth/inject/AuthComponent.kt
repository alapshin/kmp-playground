package dev.bitbakery.boilerplate.auth.inject

import de.jensklingenberg.ktorfit.Ktorfit
import dev.bitbakery.boilerplate.auth.data.AuthApi
import dev.bitbakery.boilerplate.auth.data.createAuthApi
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
interface AuthComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideAuthService(ktorfit: Ktorfit): AuthApi = ktorfit.createAuthApi()
}
