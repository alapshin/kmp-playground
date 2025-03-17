package dev.bitbakery.boilerplate.post.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bitbakery.boilerplate.viewmodel.injectedViewModel

@Composable
@Suppress("UnusedParameter")
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PostDetailViewModel =
        injectedViewModel<PostDetailViewModel, PostDetailViewModel.Factory>(
            creationCallback = { factory -> factory(1) },
        ),
) {
}
