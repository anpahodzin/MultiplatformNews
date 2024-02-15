package news.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import extension.formatDDMMYYYY_HHMM
import news.model.News
import theme.AppColors
import theme.AppTheme

@Composable
fun NewsListScreen(component: NewsListComponent) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        componentState.getNewsOrNull()?.let { newsList ->
            NewsListContent(
                newsList = newsList,
                onNewsSelected = component::onNewsSelected,
                state = rememberLazyListState()
            )
        }
        componentState.getCategoryOrNull()?.let { category ->
            NewsBottomBar(
                modifier = Modifier.align(Alignment.BottomStart).safeDrawingPadding(),
                selectedNewsCategory = category,
                onCategoryClick = component::onCategorySelected
            )
        }

        when (componentState) {
            is NewsListUiState.Data -> {}
            is NewsListUiState.Error -> {
                Text("Error")
            }

            is NewsListUiState.Loading, NewsListUiState.Initial -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = AppTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
fun NewsListContent(
    newsList: List<News>,
    onNewsSelected: (News) -> Unit,
    state: LazyListState,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = state,
    ) {
        items(newsList, key = { item: News -> item.url }) {
            NewsCard(it, onNewsSelected)
        }
    }
}

@Composable
fun NewsCard(
    news: News,
    onNewsSelected: (News) -> Unit
) {
    val imageSize = 150.dp
    val image = news.urlToImage
    Row(
        modifier = Modifier
            .widthIn(max = 700.dp)
            .wrapContentHeight()
            .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.small)
            .clip(AppTheme.shapes.large)
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

