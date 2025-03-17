package dev.bitbakery.boilerplate.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.bitbakery.boilerplate.shared.resources.Res
import dev.bitbakery.boilerplate.shared.resources.login_login_button
import dev.bitbakery.boilerplate.shared.resources.login_password_label
import dev.bitbakery.boilerplate.shared.resources.login_username_label
import dev.bitbakery.boilerplate.theme.spacing
import dev.bitbakery.boilerplate.viewmodel.injectedViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    onSuccessfulLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = injectedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val latestOnSuccessfulLogin by rememberUpdatedState(onSuccessfulLogin)

    if (state.success == true) {
        LaunchedEffect(state.success) {
            latestOnSuccessfulLogin()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
    ) {
        OutlinedTextField(
            value = state.username ?: "",
            label = { Text(stringResource(Res.string.login_username_label)) },
            singleLine = true,
            isError = state.error != null,
            onValueChange = { email -> viewModel.accept(LoginEvent.EmailInput(email)) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
        OutlinedTextField(
            value = state.password ?: "",
            label = { Text(stringResource(Res.string.login_password_label)) },
            singleLine = true,
            isError = state.error != null,
            supportingText =
                state.error?.let {
                    {
                        Text(
                            text = stringResource(it.message),
                        )
                    }
                },
            onValueChange = { password -> viewModel.accept(LoginEvent.PasswordInput(password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
        if (state.progress) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    viewModel.accept(LoginEvent.LoginClick)
                },
                contentPadding = ButtonDefaults.ContentPadding,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(Res.string.login_login_button))
            }
        }
    }
}
