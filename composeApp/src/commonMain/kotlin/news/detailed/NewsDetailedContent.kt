package news.detailed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import coil3.compose.AsyncImage
import extension.formatDDMMYYYY_HHMM
import extension.openUrl
import news.model.News
import theme.AppColors
import theme.AppTheme

@Composable
fun NewsDetailedContent(
    modifier: Modifier = Modifier,
    news: News,
) {
    Column(
        modifier = modifier
            .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val image = news.urlToImage
        if (image != null) {
            AsyncImage(
                model = image,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.large)
            )
        }
        Spacer(Modifier.height(AppTheme.sizes.large))
        Text(
            text = news.title,
            style = AppTheme.typography.lightTitleHeaderText,
            textDecoration = TextDecoration.Underline,
            color = AppTheme.colors.primary,
            modifier = Modifier.clickable { openUrl(news.url) }
        )
        Spacer(Modifier.height(AppTheme.sizes.small))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = news.sourceName,
                style = AppTheme.typography.lightText
            )
            news.author?.let {
                Text(
                    text = it,
                    style = AppTheme.typography.lightText
                )
            }
            Text(
                text = news.publishedAt.formatDDMMYYYY_HHMM(),
                style = AppTheme.typography.lightText,
                color = AppColors.gray600
            )
        }
        news.description?.let {
            Spacer(Modifier.height(AppTheme.sizes.large))
            Text(
                text = it,
                style = AppTheme.typography.lightTitleText,
            )
        }
    }
}