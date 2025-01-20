package dev.bitbakery.boilerplate.auth.data

import arrow.core.Either
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import dev.bitbakery.boilerplate.network.ApiError

interface AuthApi {
    @POST("login/")
    suspend fun login(
        @Body request: LoginRequest,
    ): Either<ApiError, LoginResponse>

    @POST("api/authorize")
    suspend fun authorize(): LoginResponse
}
