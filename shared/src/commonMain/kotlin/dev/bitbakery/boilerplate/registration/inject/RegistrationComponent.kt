package dev.bitbakery.boilerplate.registration.inject

import dev.bitbakery.boilerplate.registration.domain.RegistrationPresenter
import dev.bitbakery.boilerplate.registration.domain.RegistrationPresenterImpl
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(AppScope::class)
interface RegistrationComponent {
    @Provides
    fun provideRegistrationPresenter(presenter: RegistrationPresenterImpl): RegistrationPresenter = presenter
}
