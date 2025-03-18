package dev.bitbakery.boilerplate.post.ui

import dev.bitbakery.boilerplate.post.domain.PostDomainModel

sealed interface PostDetailState {
    data class Success(
        val post: PostDomainModel
    ): PostDetailState
    data object Loading: PostDetailState
    data object Unknown: PostDetailState
}
