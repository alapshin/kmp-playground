package dev.bitbakery.boilerplate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import dev.bitbakery.boilerplate.shared.resources.Res
import dev.bitbakery.boilerplate.shared.resources.bottom_navigation_home_label
import org.jetbrains.compose.resources.StringResource

val navigationDestinations =
    listOf(
        NavigationItem(
            icon = Icons.Default.Home,
            label = Res.string.bottom_navigation_home_label,
            destination = Destination.Home,
        ),
    )

data class NavigationItem(
    val icon: ImageVector,
    val label: StringResource,
    val destination: Destination,
    val selected: Boolean = false,
)

fun NavDestination.isTopLevel(): Boolean =
    hierarchy.any { dest ->
        navigationDestinations.any { nd ->
            dest.hasRoute(nd.destination::class)
        }
    }

fun NavDestination.isSelected(destination: Destination): Boolean = hierarchy.any { it.hasRoute(destination::class) }

/**
 * Navigate to destination and clear backstack
 */
fun NavHostController.resetTo(destination: Destination) {
    navigate(destination) {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateTo(destination: Destination) {
    navigate(destination) {
        // Pop up to the start destination of the graph to avoid building up a large
        // stack of destinations on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Restore state when re-selecting a previously selected item
        restoreState = true
        // Avoid multiple copies of the same destination when re-selecting the same item
        launchSingleTop = true
    }
}
