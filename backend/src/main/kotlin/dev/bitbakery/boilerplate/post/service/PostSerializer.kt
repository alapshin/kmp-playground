package dev.bitbakery.boilerplate.post.service

import dev.bitbakery.boilerplate.post.data.PostEntity
import dev.bitbakery.boilerplate.user.service.User
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
class PostSerializer {
    fun serialize(entity: PostEntity): Post =
        Post(
            id = entity.id,
            uuid = entity.uuid,
            title = entity.title,
            content = entity.content,
            createdAt = entity.createdAt,
            user =
                User(
                    id = entity.user.id,
                    uuid = entity.user.uuid,
                    username = entity.user.username,
                ),
            likeCount = entity.likeCount,
            commentCount = entity.commentCount,
        )
}
