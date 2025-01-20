package dev.bitbakery.boilerplate.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.bitbakery.boilerplate.base.LocalViewModelFactory
import dev.bitbakery.boilerplate.main.view.MainEvent
import dev.bitbakery.boilerplate.main.view.MainViewModel
import dev.bitbakery.boilerplate.navigation.AppBar
import dev.bitbakery.boilerplate.navigation.AppNavBar
import dev.bitbakery.boilerplate.navigation.AppNavHost
import dev.bitbakery.boilerplate.navigation.isTopLevel
import dev.bitbakery.boilerplate.navigation.navigateTo
import dev.bitbakery.boilerplate.theme.AppTheme

@Composable
fun BoilerplateApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = LocalViewModelFactory.current.request(),
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentNavDestination = currentBackStackEntry?.destination

    AppTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                if (currentNavDestination?.isTopLevel() == true) {
                    AppBar(
                        onLogoutClick = {
                            viewModel.accept(MainEvent.Logout)
                        },
                    )
                }
            },
            bottomBar = {
                AppNavBar(
                    onItemClick = { destination ->
                        navController.navigateTo(destination)
                    },
                    currentDestination = currentNavDestination,
                )
            },
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
