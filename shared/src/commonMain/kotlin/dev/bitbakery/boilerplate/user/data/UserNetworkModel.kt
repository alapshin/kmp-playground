package dev.bitbakery.boilerplate.user.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class UserNetworkModel(
    @SerialName("id")
    val id: Long,
    @SerialName("uuid")
    val uuid: Uuid,
    @SerialName("username")
    val username: String,
)
