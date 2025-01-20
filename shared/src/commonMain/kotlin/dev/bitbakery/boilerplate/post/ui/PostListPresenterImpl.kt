package dev.bitbakery.boilerplate.post.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import dev.bitbakery.boilerplate.base.DataState
import dev.bitbakery.boilerplate.post.data.PostRepository
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class PostListPresenterImpl(
    private val repository: PostRepository,
) : PostListPresenter {
    @Composable
    override fun state(events: Flow<PostListEvent>): PostListState {
        val flow =
            remember {
                repository.getPosts()
            }
        val datastate by flow.collectAsState(null)

        return datastate.let { s ->
            when (s) {
                is DataState.Initial -> PostListState()
                is DataState.Loading -> PostListState(progress = true)
                is DataState.Value -> PostListState()
                else -> PostListState()
            }
        }
    }
}
