package dev.bitbakery.boilerplate.user.data

import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

data class UserDatabaseModel(
    val id: Long,
    val uuid: Uuid,
    val username: String,
    val createdAt: Instant? = null,
)
