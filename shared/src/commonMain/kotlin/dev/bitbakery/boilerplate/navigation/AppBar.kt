package dev.bitbakery.boilerplate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bitbakery.boilerplate.shared.resources.Res
import dev.bitbakery.boilerplate.shared.resources.appbar_action_logout
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppBar(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = onLogoutClick) {
                Icon(
                    Icons.AutoMirrored.Default.Logout,
                    contentDescription = stringResource(Res.string.appbar_action_logout),
                )
            }
        },
    )
}
