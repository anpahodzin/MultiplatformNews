package view.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import theme.AppColors
import theme.AppTheme


@Composable
fun ShimmerBox(modifier: Modifier) {
    Box(
        modifier = modifier
            .shadow(
                AppTheme.sizes.elevationSmall,
                shape = AppTheme.shapes.large,
                clip = true
            )
            .background(AppTheme.colors.surface)
            .shimmerLoadingAnimation(shimmerColor = AppColors.blueGray100)
    )
}