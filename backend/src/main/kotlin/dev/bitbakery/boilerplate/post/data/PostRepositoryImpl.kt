package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boileplate.database.Database
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class PostRepositoryImpl(
    private val mapper: PostMapper,
    private val database: Database,
) : PostRepository {
    override fun getPosts(): List<PostEntity> = database.postQueries.selectAllPosts(mapper::map).executeAsList()

    override fun getPost(postId: Long): PostEntity =
        database.postQueries.selectPostById(postId, mapper::map).executeAsOne()
}
