package theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

data class ColorPalette(
    //  Material
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val background: Color,
    val surface: Color,
    val error: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onError: Color,
    val isLight: Boolean,
    //  Other
    val bottomBarColor: Color,
) {
    val materialColors
        get() = Colors(
            primary = primary,
            primaryVariant = primaryVariant,
            secondary = secondary,
            secondaryVariant = secondaryVariant,
            background = background,
            surface = surface,
            error = error,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            onError = onError,
            isLight = isLight
        )
}

fun lightColorPalette() = ColorPalette(
    // Material
    primary = AppColors.breakerBay600,
    primaryVariant = AppColors.breakerBay800,
    secondary = AppColors.breakerBay600,
    secondaryVariant = AppColors.breakerBay600,
    background = AppColors.blueGray50,
    surface = AppColors.white,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
    isLight = true,
    //  Other
    bottomBarColor = AppColors.blueGray400
)

fun darkColorPalette() = lightColorPalette().copy(
    // Material
    primary = AppColors.breakerBay600,
    primaryVariant = AppColors.breakerBay800,
    secondary = Color.White,
    secondaryVariant = Color.White,
    background = AppColors.gray900,
    surface = AppColors.gray800,
    error = Color(0xFFCF6679),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black,
    isLight = false,
    //  Other
    bottomBarColor = AppColors.blueGray800
)