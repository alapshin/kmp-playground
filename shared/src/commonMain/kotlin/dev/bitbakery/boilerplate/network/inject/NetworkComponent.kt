package dev.bitbakery.boilerplate.network.inject

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import dev.bitbakery.boilerplate.BuildKonfig
import dev.bitbakery.boilerplate.network.BooleanRequestConverter
import dev.bitbakery.boilerplate.network.EitherResponseConverter
import dev.bitbakery.boilerplate.network.KermitKtorLogger
import dev.bitbakery.boilerplate.network.TokenStorage
import dev.bitbakery.boilerplate.network.TokenStorageImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.IntoSet
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
interface NetworkComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun json(): Json =
        Json {
            prettyPrint = true
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

    @Provides
    @SingleIn(AppScope::class)
    fun KermitKtorLogger.bind(): Logger = this

    @Provides
    @SingleIn(AppScope::class)
    fun TokenStorageImpl.bind(): TokenStorage = this

    @Provides
    @SingleIn(AppScope::class)
    fun httpClient(
        json: Json,
        logger: Logger,
        engine: HttpClientEngine,
        tokenStorage: TokenStorage,
    ): HttpClient =
        HttpClient(engine) {
            expectSuccess = true
            install(Auth) {
                bearer {
                    loadTokens {
                        tokenStorage.getToken()?.let { token ->
                            BearerTokens(token, "")
                        }
                    }
                }
            }
            install(Logging) {
                level = LogLevel.ALL
                this.logger = logger
            }
            install(ContentNegotiation) {
                json(json)
            }
            install(DefaultRequest) {
                url(BuildKonfig.API_BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }

    @Provides
    @SingleIn(AppScope::class)
    @Suppress("SpreadOperator")
    fun ktorfit(
        httpClient: HttpClient,
        converterFactories: Set<Converter.Factory>,
    ): Ktorfit =
        Ktorfit
            .Builder()
            .baseUrl(BuildKonfig.API_BASE_URL)
            .httpClient(httpClient)
            .converterFactories(*converterFactories.toTypedArray())
            .build()

    @IntoSet
    @Provides
    @SingleIn(AppScope::class)
    fun BooleanRequestConverter.Factory.bind(): Converter.Factory = this

    @IntoSet
    @Provides
    @SingleIn(AppScope::class)
    fun EitherResponseConverter.Factory.bind(): Converter.Factory = this
}
