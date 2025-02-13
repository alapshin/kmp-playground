package dev.bitbakery.boilerplate.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bitbakery.boilerplate.theme.spacing

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding =
            PaddingValues(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.medium,
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
    ) {
        TODO()
    }
}
