package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boileplate.database.Database
import kotlinx.datetime.Instant
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class PostRepositoryImpl(
    private val database: Database,
) : PostRepository {
    override fun getPosts(): List<PostEntity> =
        database.postQueries
            .selectAll { id, title, content, createdAt ->
                PostEntity(
                    id = id,
                    title = title,
                    content = content,
                    createdAt = Instant.parse(createdAt),
                )
            }.executeAsList()
}
