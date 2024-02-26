package news.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import extension.pxToDp
import multiplatformnews.composeapp.generated.resources.Res
import news.list.NewsCard
import news.model.News
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun NewsFavoriteScreen(component: NewsFavoriteComponent) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        var bottomBarSize by remember { mutableStateOf(IntSize.Zero) }
        val lazyListState = rememberLazyListState()

        when (val state = componentState) {
            is NewsFavoriteUiState.Data -> {
                NewsFavoriteListContent(
                    newsList = state.favoriteNews,
                    onNewsSelected = {},
                    bottomPadding = bottomBarSize.height.pxToDp(),
                    lazyListState = lazyListState
                )
            }

            is NewsFavoriteUiState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(Res.string.something_went_wrong), fontSize = 20.sp)
                    Text(
                        modifier = Modifier
                            .padding(AppTheme.sizes.medium)
                            .clip(AppTheme.shapes.circle)
                            .background(AppTheme.colors.primary)
                            .clickable { /*component.refresh() */ }
                            .padding(AppTheme.sizes.medium),
                        text = stringResource(Res.string.try_again),
                        fontSize = 20.sp,
                        color = AppTheme.colors.onPrimary
                    )
                }
            }

            is NewsFavoriteUiState.Loading, NewsFavoriteUiState.Initial -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = AppTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
private fun NewsFavoriteListContent(
    newsList: List<News>,
    onNewsSelected: (News) -> Unit,
    bottomPadding: Dp,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val insets = WindowInsets.safeDrawing.asPaddingValues()
    val maxContentWidth = AppTheme.sizes.maxContentWidth
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = insets.calculateTopPadding(),
            bottom = insets.calculateBottomPadding() + bottomPadding
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState,
    ) {
        item {
            Text(
                text = stringResource(Res.string.favorite),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.typography.lightTitleHeaderText,
                fontSize = 40.sp
            )
        }
        items(newsList, key = { item: News -> item.url }) {
            NewsCard(
                modifier = Modifier.widthIn(max = maxContentWidth),
                news = it,
                onNewsSelected = onNewsSelected
            )
        }
    }
}