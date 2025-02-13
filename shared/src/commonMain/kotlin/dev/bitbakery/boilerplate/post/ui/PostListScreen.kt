package dev.bitbakery.boilerplate.post.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teobaranga.kotlin.inject.viewmodel.runtime.compose.injectedViewModel
import dev.bitbakery.boilerplate.theme.spacing

@Composable
fun PostListScreen(
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostListViewModel = injectedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
        contentPadding =
            PaddingValues(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.medium,
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
    ) {
        items(state.items, key = { it.id }) {
            PostListItemUi(it, onItemClick = onItemClick)
        }
    }
}
