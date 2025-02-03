package dev.bitbakery.boilerplate.post.data

import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class PostEntity(
    val id: Long,
    val uuid: Uuid,
    val userId: Long,
    val title: String,
    val content: String,
    val createdAt: Instant,
)
