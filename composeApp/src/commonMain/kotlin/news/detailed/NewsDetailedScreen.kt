package news.detailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.seiko.imageloader.rememberImagePainter
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import extension.formatDDMMYYYY_HHMM
import extension.openUrl
import org.example.kmpnews.MR
import theme.AppColors
import theme.AppTheme

@Composable
fun NewsDetailedScreen(component: NewsDetailedComponent) {
    val news by component.state.subscribeAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        val buttonBackSize = 40.dp
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .safeDrawingPadding()
                .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.small)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(buttonBackSize)
                    .clip(AppTheme.shapes.circle)
                    .clickable { component.onBackPressed() }
                    .background(AppTheme.colors.surface)
                    .padding(AppTheme.sizes.small),
                painter = painterResource(MR.images.ic_back),
                contentDescription = stringResource(MR.strings.back)
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = news.sourceName,
                style = AppTheme.typography.lightTitleHeaderText,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .padding(top = buttonBackSize + AppTheme.sizes.medium)
                .padding(horizontal = AppTheme.sizes.medium),
        ) {
            val image = news.urlToImage
            if (image != null) {
                Image(
                    painter = rememberImagePainter(image),
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
                color = AppColors.dodgerBlue400,
                modifier = Modifier.clickable { openUrl(news.url) }
            )
            Spacer(Modifier.height(AppTheme.sizes.small))
            Column {
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
}