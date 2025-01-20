package dev.bitbakery.boilerplate.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppNavBar(
    onItemClick: (Destination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    if (currentDestination?.isTopLevel() == true) {
        NavigationBar(modifier = modifier) {
            navigationDestinations.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(stringResource(item.label))
                    },
                    onClick = { onItemClick(item.destination) },
                    selected = currentDestination.isSelected(item.destination),
                )
            }
        }
    }
}
