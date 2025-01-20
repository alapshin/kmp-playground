package dev.bitbakery.boilerplate.post.domain

import kotlinx.datetime.Instant

data class PostDomainModel(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Instant,
)
