package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import dev.bitbakery.boilerplate.user.domain.UserDomainModel
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import kotlin.uuid.Uuid

@Inject
@SingleIn(AppScope::class)
@Suppress("LongParameterList")
class PostMapper {
    fun map(
        id: Long,
        uuid: String,
        userId: Long,
        title: String,
        content: String,
        createdAt: String,
        userUuid: String,
        username: String,
        likeCount: Long,
        commentCount: Long,
    ): PostDomainModel =
        PostDomainModel(
            id = id,
            uuid = Uuid.parse(uuid),
            title = title,
            content = content,
            createdAt = Instant.parse(createdAt),
            user =
                UserDomainModel(
                    id = userId,
                    uuid = Uuid.parse(userUuid),
                    username = username,
                ),
            likeCount = likeCount,
            commentCount = commentCount,
        )
}
