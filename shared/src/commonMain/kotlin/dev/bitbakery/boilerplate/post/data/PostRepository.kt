package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.base.DataError
import dev.bitbakery.boilerplate.base.DataState
import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<DataState<DataError, List<PostDomainModel>>>
}
