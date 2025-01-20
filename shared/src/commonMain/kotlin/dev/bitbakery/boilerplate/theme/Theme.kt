@file:Suppress(
    "CompositionLocalAllowlist",
    "ktlint:compose:compositionlocal-allowlist",
)

package dev.bitbakery.boilerplate.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

val LocalSpacing = compositionLocalOf { Spacing() }

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors =
        if (useDarkTheme) {
            DarkColors
        } else {
            LightColors
        }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
    ) {
        MaterialTheme(
            colorScheme = colors,
            content = content,
        )
    }
}
