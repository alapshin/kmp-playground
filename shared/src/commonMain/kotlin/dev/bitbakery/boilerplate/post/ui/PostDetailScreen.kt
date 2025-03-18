package dev.bitbakery.boilerplate.post.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.bitbakery.boilerplate.viewmodel.injectedViewModel

@Composable
@Suppress("UnusedParameter")
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PostDetailViewModel = injectedViewModel<PostDetailViewModel, PostDetailViewModel.Factory>(
        creationCallback = { factory -> factory(1) },
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when(val s = state) {
        is PostDetailState.Loading -> {
        }
        is PostDetailState.Success -> {
            Text(
                text = s.post.content
            )
        }
        else -> {}
    }
}
