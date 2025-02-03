package dev.bitbakery.boilerplate.post.ui

import kotlinx.datetime.LocalDateTime

data class PostUiModel(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val username: String,
    val likeCount: Long,
    val commentCount: Long,
)
