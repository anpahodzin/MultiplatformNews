package news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import theme.AppTheme

@Composable
fun NewsHeader(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
) {
    Column(
        modifier = modifier
            .padding(horizontal = AppTheme.sizes.large, vertical = AppTheme.sizes.small)
    ) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            style = AppTheme.typography.lightTitleHeaderText,
            fontSize = 40.sp
        )
        subTitle?.let {
            Text(
                text = it,
                style = AppTheme.typography.lightTitleHeaderText,
                fontSize = 30.sp
            )
        }
    }
}