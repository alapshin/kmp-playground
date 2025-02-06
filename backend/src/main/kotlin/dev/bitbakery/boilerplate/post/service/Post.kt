package dev.bitbakery.boilerplate.post.service

import dev.bitbakery.boilerplate.user.service.User
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class Post(
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
    val user: User,
    @SerialName("like_count")
    val likeCount: Long,
    @SerialName("comment_count")
    val commentCount: Long,
)
