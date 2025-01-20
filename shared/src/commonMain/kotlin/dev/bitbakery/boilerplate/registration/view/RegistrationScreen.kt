package dev.bitbakery.boilerplate.registration.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.bitbakery.boilerplate.shared.resources.Res
import dev.bitbakery.boilerplate.shared.resources.registration_register_button
import dev.bitbakery.boilerplate.theme.spacing
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier) {
//    val state by bloc.state.collectAsState()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

//    if (state.success == true) {
//        LaunchedEffect(state) {
//            bloc.navigateToMain()
//        }
//    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier =
            modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium),
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
        Button(
            onClick = {
            },
            contentPadding = ButtonDefaults.ContentPadding,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(Res.string.registration_register_button))
        }
    }
}
