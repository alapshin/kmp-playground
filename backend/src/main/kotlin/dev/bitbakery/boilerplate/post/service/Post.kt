package dev.bitbakery.boilerplate.post.service

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Post(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: Instant,
)
