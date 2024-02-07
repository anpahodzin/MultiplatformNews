package org.example.kmpnews.news.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import news.News
import news.component.NewsListComponent
import news.component.NewsListUiState
import org.example.kmpnews.theme.AppColors
import org.example.kmpnews.theme.AppTheme

@Composable
fun NewsListScreen(component: NewsListComponent) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = componentState) {
            is NewsListUiState.Data -> NewsListContent(state.newsList)
            NewsListUiState.Error -> {}
            NewsListUiState.Loading -> {}
        }
    }
}

@Composable
fun NewsListContent(newsList: List<News>) {
    WindowInsets.safeDrawing.asPaddingValues()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(newsList, key = { item: News -> item.url }) {
            NewsCard(it)
        }
    }
}

@Composable
fun NewsCard(news: News) {
    val imageSize = 150.dp
    val image = news.urlToImage
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = AppTheme.sizes.medium, vertical = AppTheme.sizes.small)
            .clip(AppTheme.shapes.large)
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
            Text(
                text = news.sourceName,
                maxLines = 1,
                fontSize = 12.sp,
                style = AppTheme.typography.lightText
            )
            Text(
                text = news.publishedAt.toString(),
                maxLines = 1,
                fontSize = 12.sp,
                style = AppTheme.typography.lightText,
                color = AppColors.gray600
            )
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

