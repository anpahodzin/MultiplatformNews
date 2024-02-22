package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import multiplatformnews.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun ButtonBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(AppTheme.shapes.circle)
            .clickable(onClick = onClick)
            .background(AppTheme.colors.surface)
            .padding(AppTheme.sizes.small),
        painter = painterResource(Res.drawable.ic_arrow_back_24),
        contentDescription = stringResource(Res.string.back)
    )
}