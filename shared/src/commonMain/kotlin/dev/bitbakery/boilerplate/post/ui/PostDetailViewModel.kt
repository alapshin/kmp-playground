package dev.bitbakery.boilerplate.post.ui

import androidx.compose.runtime.Composable
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import dev.bitbakery.boilerplate.post.data.PostRepository
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
@Suppress("UnusedPrivateProperty")
class PostDetailViewModel(
    @Assisted
    private val postId: Int,
    private val repository: PostRepository,
) : MolecularViewModel<PostDetailEvent, PostDetailState>() {
    @Inject
    class Factory(
        private val factory: (Int) -> PostDetailViewModel,
    ) {
        operator fun invoke(postId: Int): PostDetailViewModel = factory(postId)
    }

    @Composable
    override fun state(events: Flow<PostDetailEvent>): PostDetailState = PostDetailState(progress = false)
}
