package dev.bitbakery.boilerplate.post.ui

import dev.bitbakery.boilerplate.base.Error

data class PostListState(
    val error: Error? = null,
    val progress: Boolean = false,
    val items: List<PostUiModel> = emptyList(),
)
