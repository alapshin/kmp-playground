package dev.bitbakery.boilerplate.post.ui

sealed interface PostDetailEvent {
    data class Load(
        val postId: Long,
    ) : PostDetailEvent

    data object ToggleLike : PostDetailEvent
}
