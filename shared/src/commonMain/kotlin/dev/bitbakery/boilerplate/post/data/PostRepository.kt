package dev.bitbakery.boilerplate.post.data

import dev.bitbakery.boilerplate.base.DataState
import dev.bitbakery.boilerplate.network.ApiError
import dev.bitbakery.boilerplate.post.domain.PostDomainModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<DataState<ApiError, List<PostDomainModel>>>
}
