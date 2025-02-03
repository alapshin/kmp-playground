package dev.bitbakery.boilerplate.post.ui

import dev.bitbakery.boilerplate.base.DataError

data class PostListState(
    val error: DataError? = null,
    val progress: Boolean = false,
    val items: List<PostUiModel> = emptyList(),
)
