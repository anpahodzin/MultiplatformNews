package theme

import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun AppTheme(
    colorPalette: ColorPalette = lightColorPalette(),
    typography: AppTypography = AppTypography(),
    shapes: AppShapes = AppShapes(),
    sizes: AppSizes = AppSizes(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colorPalette,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        LocalSize provides sizes,
        LocalContentColor provides colorPalette.onSurface
    ) {
        MaterialTheme(
            colors = colorPalette.materialColors,
            typography = typography.materialTypography,
            shapes = shapes.materialShapes,
            content = content
        )
    }
}

object AppTheme {

    val colors: ColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val sizes: AppSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalSize.current
}

internal val LocalColors = compositionLocalOf { lightColorPalette() }
internal val LocalShapes = staticCompositionLocalOf { AppShapes() }
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
internal val LocalSize = staticCompositionLocalOf { AppSizes() }