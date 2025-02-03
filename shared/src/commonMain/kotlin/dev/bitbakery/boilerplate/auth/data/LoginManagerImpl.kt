package dev.bitbakery.boilerplate.auth.data

import arrow.core.Either
import arrow.core.right
import dev.bitbakery.boilerplate.network.ApiError
import dev.bitbakery.boilerplate.network.TokenStorage
import dev.bitbakery.boilerplate.network.toApiError
import dev.bitbakery.boilerplate.user.data.UserNetworkModel
import dev.bitbakery.boilerplate.user.data.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.authProvider
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class LoginManagerImpl(
    private val api: AuthApi,
    private val httpClient: HttpClient,
    private val tokenStorage: TokenStorage,
    private val userRepository: UserRepository,
) : LoginManager {
    private val isUserLoggedIn = MutableStateFlow(false)

    override fun isLoggedIn(): Flow<Boolean> = isUserLoggedIn.asStateFlow()

    override suspend fun login(
        username: String,
        password: String,
    ): Either<ApiError, UserNetworkModel> =
        api
            .login(LoginRequest(username = username, password = password))
            .map { response ->
                response.user
            }.onRight { user ->
                userRepository.setCurrentUser(user)
                isUserLoggedIn.update { true }
            }

    override suspend fun logout() {
        setBearerToken(null)
        isUserLoggedIn.update { false }
    }

    override suspend fun authorize(): Either<ApiError, UserNetworkModel?> =
        if (tokenStorage.getToken() == null) {
            null.right()
        } else {
            Either
                .catch {
                    api
                        .authorize()
                }.map { response ->
                    response.user
                }.mapLeft { throwable ->
                    throwable.toApiError()
                }.onRight {
                    isUserLoggedIn.update { true }
                }
        }

    private suspend fun setBearerToken(token: String?) {
        tokenStorage.setToken(token)
        // Invalidate cached token in http client
        httpClient.authProvider<BearerAuthProvider>()?.clearToken()
    }
}
