package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.base.Error
import dev.bitbakery.boilerplate.base.Result
import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<Result<Error, List<PostDomainModel>>>

    fun getPost(postId: Long): Flow<Result<Error, PostDomainModel>>
}
