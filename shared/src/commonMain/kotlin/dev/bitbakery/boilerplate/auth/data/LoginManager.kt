package dev.bitbakery.boilerplate.auth.data

import arrow.core.Either
import dev.bitbakery.boilerplate.network.ApiError
import dev.bitbakery.boilerplate.user.data.User
import kotlinx.coroutines.flow.Flow

interface LoginManager {
    fun isLoggedIn(): Flow<Boolean>

    suspend fun login(
        username: String,
        password: String,
    ): Either<ApiError, User>

    suspend fun logout()

    suspend fun authorize(): Either<ApiError, User?>
}
