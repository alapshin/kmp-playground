package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boileplate.database.Database
import dev.bitbakery.boilerplate.user.data.UserEntity
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import kotlin.uuid.Uuid

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class PostRepositoryImpl(
    private val database: Database,
) : PostRepository {
    override fun getPosts(): List<PostEntity> =
        database.postQueries
            .selectFull { id, uuid, userId, title, content, createdAt, user_uuid, username, likeCount, commentCount ->
                PostEntity(
                    id = id,
                    uuid = Uuid.parse(uuid),
                    title = title,
                    content = content,
                    createdAt = Instant.parse(createdAt),
                    user =
                        UserEntity(
                            id = userId,
                            uuid = Uuid.parse(user_uuid),
                            username = username,
                        ),
                    likeCount = likeCount,
                    commentCount = commentCount,
                )
            }.executeAsList()
}
