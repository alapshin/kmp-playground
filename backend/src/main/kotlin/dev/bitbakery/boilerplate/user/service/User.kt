package dev.bitbakery.boilerplate.user.service

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class User(
    @SerialName("id")
    private val id: Long,
    @SerialName("uuid")
    private val uuid: Uuid,
    @SerialName("username")
    private val username: String,
    @SerialName("created_at")
    private val createdAt: Instant? = null,
)
