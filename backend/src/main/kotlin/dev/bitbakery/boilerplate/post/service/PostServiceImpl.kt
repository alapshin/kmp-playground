package dev.bitbakery.boilerplate.post.service

import dev.bitbakery.boilerplate.post.data.PostRepository
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class PostServiceImpl(
    private val serializer: PostSerializer,
    private val repository: PostRepository,
) : PostService {
    override fun getPosts(): List<Post> = repository.getPosts().map(serializer::serialize)

    override fun getPost(postId: Long): Post = repository.getPost(postId).let(serializer::serialize)
}
