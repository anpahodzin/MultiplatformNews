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
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.example.kmpnews.MR
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
        painter = painterResource(MR.images.ic_back),
        contentDescription = stringResource(MR.strings.back)
    )
}