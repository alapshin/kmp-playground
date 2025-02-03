package dev.bitbakery.boilerplate.post.domain

import dev.bitbakery.boilerplate.user.domain.UserDomainModel
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class PostDomainModel(
    val id: Long,
    val uuid: Uuid,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val likeCount: Long,
    val commentCount: Long,
    val user: UserDomainModel,
)
