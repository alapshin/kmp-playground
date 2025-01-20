package dev.bitbakery.boilerplate.post.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.bitbakery.boilerplate.base.injectViewModel
import dev.bitbakery.boilerplate.theme.spacing

@Composable
fun PostListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostListViewModel = injectViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding =
            PaddingValues(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.medium,
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
    ) {
    }
}
