package dev.bitbakery.boilerplate.post.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import co.touchlab.kermit.Logger
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import dev.bitbakery.boilerplate.base.DataState
import dev.bitbakery.boilerplate.post.data.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class PostListViewModel(
    private val repository: PostRepository,
) : MolecularViewModel<PostListEvent, PostListState>() {
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
                is DataState.Failure -> {
                    Logger.e {
                        s.error.toString()
                    }
                    PostListState()
                }
                is DataState.Success ->
                    PostListState(
                        items =
                            s.value.map {
                                PostUiModel(
                                    id = it.id,
                                    title = it.title,
                                    content = it.content,
                                    username = it.user.username,
                                    createdAt = it.createdAt.toLocalDateTime(TimeZone.currentSystemDefault()),
                                    likeCount = it.likeCount,
                                    commentCount = it.commentCount,
                                )
                            },
                    )
                else -> PostListState()
            }
        }
    }
}
