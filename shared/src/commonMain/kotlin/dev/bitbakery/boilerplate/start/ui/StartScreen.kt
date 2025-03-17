package dev.bitbakery.boilerplate.start.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.bitbakery.boilerplate.shared.resources.Res
import dev.bitbakery.boilerplate.shared.resources.landing_login_button
import dev.bitbakery.boilerplate.shared.resources.landing_register_button
import dev.bitbakery.boilerplate.theme.spacing
import dev.bitbakery.boilerplate.viewmodel.injectedViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
@Suppress("LongParameterList") // We need those callbacks
fun StartScreen(
    onSuccessfulLogin: () -> Unit,
    onLoginButtonClick: () -> Unit,
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StartViewModel = injectedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val latestOnSuccessfulLogin by rememberUpdatedState(onSuccessfulLogin)

    if (state.success == true) {
        LaunchedEffect(state.success) {
            latestOnSuccessfulLogin()
        }
    }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
        contentAlignment = Alignment.Center,
    ) {
        if (state.progress) {
            CircularProgressIndicator()
        } else if (state.success != true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = onLoginButtonClick,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = ButtonDefaults.ContentPadding,
                ) {
                    Text(stringResource(Res.string.landing_login_button))
                }
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
                FilledTonalButton(
                    onClick = onRegistrationButtonClick,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = ButtonDefaults.ContentPadding,
                ) {
                    Text(stringResource(Res.string.landing_register_button))
                }
            }
        }
    }
}
