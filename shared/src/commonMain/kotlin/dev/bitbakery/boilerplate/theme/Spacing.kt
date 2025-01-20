package dev.bitbakery.boilerplate.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val default: Dp = SpacingDefaults.DEFAULT.dp,
    val none: Dp = SpacingDefaults.NONE.dp,
    val tiny: Dp = SpacingDefaults.TINY.dp,
    val extraSmall: Dp = SpacingDefaults.EXTRA_SMALL.dp,
    val small: Dp = SpacingDefaults.SMALL.dp,
    val medium: Dp = SpacingDefaults.MEDIUM.dp,
    val large: Dp = SpacingDefaults.LARGE.dp,
)

internal object SpacingDefaults {
    internal const val NONE = 0
    internal const val TINY = 2
    internal const val EXTRA_SMALL = 4
    internal const val SMALL = 8
    internal const val MEDIUM = 16
    internal const val LARGE = 64
    internal const val DEFAULT = SMALL
}
