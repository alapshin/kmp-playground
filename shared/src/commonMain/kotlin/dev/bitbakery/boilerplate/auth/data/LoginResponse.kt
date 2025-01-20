package dev.bitbakery.boilerplate.auth.data

import dev.bitbakery.boilerplate.user.data.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("user")
    val user: User,
)
