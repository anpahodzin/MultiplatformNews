package news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import extension.formatDDMMYYYY_HHMM
import news.model.News
import theme.AppColors
import theme.AppTheme

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    imageSize: Dp = 150.dp,
    news: News,
    onNewsSelected: (News) -> Unit
) {
    val image = news.urlToImage
    Row(
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.small)
            .shadow(AppTheme.sizes.elevationSmall, shape = AppTheme.shapes.large, clip = true)
            .clickable { onNewsSelected(news) }
            .background(AppTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .weight(3f, fill = true)
                .run {
                    if (image != null) {
                        this.height(imageSize)
                    } else {
                        this.wrapContentHeight()
                    }
                }
                .padding(AppTheme.sizes.medium),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = news.title,
                maxLines = 3,
                style = AppTheme.typography.lightTitleHeaderText,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Column {
                Text(
                    text = news.sourceName,
                    maxLines = 1,
                    fontSize = 12.sp,
                    style = AppTheme.typography.lightText
                )
                Text(
                    text = news.publishedAt.formatDDMMYYYY_HHMM(),
                    maxLines = 1,
                    fontSize = 12.sp,
                    style = AppTheme.typography.lightText,
                    color = AppColors.gray600
                )
            }
        }

        if (image != null) {
            Image(
                painter = rememberImagePainter(image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageSize)
                    .clip(AppTheme.shapes.large)
            )
        }
    }
}