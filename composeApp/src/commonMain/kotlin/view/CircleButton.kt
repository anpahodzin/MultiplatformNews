package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import theme.AppTheme


@Composable
fun Modifier.circleButton(onClick: () -> Unit) =
    this.size(40.dp)
        .clip(AppTheme.shapes.circle)
        .clickable(onClick = onClick)
        .background(AppTheme.colors.surface)
        .padding(AppTheme.sizes.small)
