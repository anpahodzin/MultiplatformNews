package news.detailed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import theme.AppTheme
import view.ButtonBack

@Composable
fun NewsToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    title: String,
) {
    Box(
        modifier = modifier
            .zIndex(1f)
            .safeDrawingPadding()
            .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.small),
        contentAlignment = Alignment.Center,
    ) {
        ButtonBack(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBackClick
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = AppTheme.typography.lightTitleHeaderText,
            textAlign = TextAlign.Center
        )
    }
}