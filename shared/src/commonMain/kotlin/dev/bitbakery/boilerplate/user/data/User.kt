package dev.bitbakery.boilerplate.user.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: String,
    @SerialName("token")
    val token: String,
    @SerialName("username")
    val username: String,
)
