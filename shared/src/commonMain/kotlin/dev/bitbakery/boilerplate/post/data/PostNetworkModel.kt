package dev.bitbakery.boilerplate.post.data

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostNetworkModel(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: Instant,
)
