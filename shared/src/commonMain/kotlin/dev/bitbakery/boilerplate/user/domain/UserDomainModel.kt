package dev.bitbakery.boilerplate.user.domain

import kotlin.uuid.Uuid

data class UserDomainModel(
    val id: Long,
    val uuid: Uuid,
    val username: String,
)
