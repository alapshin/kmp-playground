package dev.bitbakery.boilerplate.database

import io.ktor.server.config.ApplicationConfig
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class DatabaseUrlProviderImpl(
    private val config: ApplicationConfig,
) : DatabaseUrlProvider {
    companion object {
        const val DATABASE_URL_PATH = "database.url"
    }

    override val url: String?
        get() = config.propertyOrNull(DATABASE_URL_PATH)?.getString()
}
