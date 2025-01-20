package dev.bitbakery.boilerplate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.bitbakery.boilerplate.login.ui.LoginScreen
import dev.bitbakery.boilerplate.post.ui.PostListScreen
import dev.bitbakery.boilerplate.start.ui.StartScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.PostList,
        modifier = modifier,
    ) {
        composable<Destination.PostList> {
            PostListScreen()
        }
        composable<Destination.Start> {
            StartScreen(
                onSuccessfulLogin = {
                    navController.resetTo(Destination.Home)
                },
                onLoginButtonClick = {
                    navController.navigate(Destination.Login) {
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                onRegistrationButtonClick = {
                    navController.navigate(Destination.Registration)
                },
            )
        }
        composable<Destination.Login> {
            LoginScreen(
                onSuccessfulLogin = {
                    navController.resetTo(Destination.Home)
                },
            )
        }
        composable<Destination.Registration> {
        }
    }
}
