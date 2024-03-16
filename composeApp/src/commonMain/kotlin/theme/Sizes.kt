package theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSizes internal constructor(
    val smaller: Dp = 4.dp,
    val small: Dp = 8.dp,
    val smallExtra: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val mediumExtra: Dp = 24.dp,
    val large: Dp = 32.dp,
    val larger: Dp = 64.dp,

    val borderSmall: Dp = 1.dp,
    val borderMedium: Dp = 2.dp,

    val elevationSmall: Dp = 2.dp,
    val elevationMedium: Dp = 4.dp,
    val elevationLarge: Dp = 8.dp,

    val maxContentWidth: Dp = 700.dp
)