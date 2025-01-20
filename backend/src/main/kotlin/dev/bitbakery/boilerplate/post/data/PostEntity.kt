package dev.bitbakery.boilerplate.post.data

import kotlinx.datetime.Instant

data class PostEntity(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Instant,
)
