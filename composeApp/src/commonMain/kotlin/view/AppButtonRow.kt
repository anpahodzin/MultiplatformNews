package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import theme.AppTheme

@Composable
fun AppButtonRow(
    modifier: Modifier = Modifier,
    elevation: Dp = AppTheme.sizes.elevationMedium,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    content: @Composable RowScope.(isSelected: Boolean, contrastColor: Color) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(vertical = AppTheme.sizes.small, horizontal = AppTheme.sizes.small)
            .shadow(elevation, AppTheme.shapes.circle)
            .background(if (isSelected) AppTheme.colors.secondary else AppTheme.colors.onSecondary)
            .clickable(onClick = onClick)
            .padding(AppTheme.sizes.smallExtra),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content(
            isSelected,
            if (isSelected) AppTheme.colors.onSecondary else AppTheme.colors.secondary
        )
    }
}