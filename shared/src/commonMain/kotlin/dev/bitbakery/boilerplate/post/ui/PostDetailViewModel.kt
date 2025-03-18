package dev.bitbakery.boilerplate.post.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import co.touchlab.kermit.Logger
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import dev.bitbakery.boilerplate.base.Result
import dev.bitbakery.boilerplate.post.data.PostRepository
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
@Suppress("UnusedPrivateProperty")
class PostDetailViewModel(
    @Assisted
    private val postId: Long,
    private val repository: PostRepository,
) : MolecularViewModel<PostDetailEvent, PostDetailState>() {
    @Inject
    class Factory(
        private val factory: (Long) -> PostDetailViewModel,
    ) {
        operator fun invoke(postId: Long): PostDetailViewModel = factory(postId)
    }

    @Composable
    override fun state(events: Flow<PostDetailEvent>): PostDetailState {
        val flow = remember {
            repository.getPost(postId)
        }
        val result by flow.collectAsState(null)

        return when (val r = result) {
            is Result.Initial -> PostDetailState.Loading
            is Result.Failure -> {
                Logger.e(r.error.toString())
                PostDetailState.Unknown
            }
            is Result.Success -> PostDetailState.Success(r.value)
            else -> PostDetailState.Unknown
        }
    }
}
