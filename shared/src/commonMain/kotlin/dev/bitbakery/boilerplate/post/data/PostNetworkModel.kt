package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.user.data.UserNetworkModel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class PostNetworkModel(
    @SerialName("id")
    val id: Long,
    @SerialName("uuid")
    val uuid: Uuid,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("user")
    val user: UserNetworkModel,
)
