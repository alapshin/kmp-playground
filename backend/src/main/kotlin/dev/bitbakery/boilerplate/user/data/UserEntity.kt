package dev.bitbakery.boilerplate.user.data

import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class UserEntity(
    val id: Long,
    val uuid: Uuid,
    val username: String,
    val createdAt: Instant? = null,
)
