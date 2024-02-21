package news.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import multiplatformnews.composeapp.generated.resources.Res
import news.model.NewsCategory
import news.toLocalizedString
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun NewsListHeader(
    modifier: Modifier = Modifier,
    category: NewsCategory
) {
    Column(
        modifier = modifier
            .padding(horizontal = AppTheme.sizes.large, vertical = AppTheme.sizes.small)
    ) {
        Text(
            text = stringResource(Res.string.top_headlines),
            modifier = Modifier.fillMaxWidth(),
            style = AppTheme.typography.lightTitleHeaderText,
            fontSize = 40.sp
        )
        Text(
            text = category.toLocalizedString(),
            style = AppTheme.typography.lightTitleHeaderText,
            fontSize = 30.sp
        )
    }
}