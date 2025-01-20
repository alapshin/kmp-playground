package dev.bitbakery.boilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import dev.bitbakery.boilerplate.app.App
import dev.bitbakery.boilerplate.inject.AndroidApplicationComponent
import dev.bitbakery.boilerplate.inject.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val appComponent =
            AndroidApplicationComponent::class.create(
                applicationContext,
            )

        val viewModelFactory = appComponent.viewModelFactory
        val imageLoaderFactory = appComponent.imageLoaderFactory

        setContent {
            App(
                viewModelFactory = viewModelFactory,
                imageLoaderFactory = imageLoaderFactory,
            )

            // TODO: Remove when https://issuetracker.google.com/issues/364713509 is fixed
            LaunchedEffect(isSystemInDarkTheme()) {
                enableEdgeToEdge()
            }
        }
    }
}
