package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.user.data.UserEntity
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class PostEntity(
    val id: Long,
    val uuid: Uuid,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val user: UserEntity,
    val likeCount: Long,
    val commentCount: Long,
)
