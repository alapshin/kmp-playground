package dev.bitbakery.boilerplate.post.ui

import androidx.compose.runtime.Composable
import com.teobaranga.kotlin.inject.viewmodel.runtime.ContributesViewModel
import dev.bitbakery.boilerplate.arch.MolecularViewModel
import dev.bitbakery.boilerplate.post.data.PostRepository
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.AppScope

@Suppress("UnusedPrivateProperty")
@Inject
@ContributesViewModel(AppScope::class)
class PostDetailViewModel(
    private val repository: PostRepository,
) : MolecularViewModel<PostDetailEvent, PostDetailState>() {
    @Composable
    override fun state(events: Flow<PostDetailEvent>): PostDetailState = PostDetailState(progress = false)
}
