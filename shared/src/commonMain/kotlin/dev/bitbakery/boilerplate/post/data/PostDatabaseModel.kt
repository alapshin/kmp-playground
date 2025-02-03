package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.user.data.UserDatabaseModel
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class PostDatabaseModel(
    val id: Long,
    val uuid: Uuid,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val user: UserDatabaseModel,
)
