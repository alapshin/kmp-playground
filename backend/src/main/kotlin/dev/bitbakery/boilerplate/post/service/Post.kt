package dev.bitbakery.boilerplate.post.service

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
class Post(
    @SerialName("id")
    val id: Long,
    @SerialName("uuid")
    val uuid: Uuid,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: Instant,
)
