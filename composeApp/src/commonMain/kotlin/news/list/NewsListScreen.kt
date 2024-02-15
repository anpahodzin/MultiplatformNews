package news.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import dev.icerock.moko.resources.compose.stringResource
import extension.pxToDp
import news.model.News
import news.model.NewsCategory
import org.example.kmpnews.MR
import theme.AppTheme

@Composable
fun NewsListScreen(component: NewsListComponent) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        var bottomBarSize by remember { mutableStateOf(IntSize.Zero) }
        val lazyListState = rememberLazyListState()

        componentState.getCategoryOrNull()?.let { category ->

            componentState.getNewsOrNull()?.let { newsList ->
                NewsListContent(
                    newsList = newsList,
                    category = category,
                    onNewsSelected = component::onNewsSelected,
                    bottomPadding = bottomBarSize.height.pxToDp(),
                    lazyListState = lazyListState
                )
            }

            NewsBottomBar(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .safeDrawingPadding()
                    .onSizeChanged { bottomBarSize = it },
                selectedNewsCategory = category,
                onCategoryClick = component::onCategorySelected
            )
        }

        when (val state = componentState) {
            is NewsListUiState.Data -> {
                LaunchedEffect(state) {
                    lazyListState.scrollToItem(0)
                }
            }

            is NewsListUiState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(MR.strings.something_went_wrong), fontSize = 20.sp)
                    Text(
                        modifier = Modifier
                            .padding(AppTheme.sizes.medium)
                            .clip(AppTheme.shapes.circle)
                            .background(AppTheme.colors.primary)
                            .clickable { component.refresh() }
                            .padding(AppTheme.sizes.medium),
                        text = stringResource(MR.strings.try_again),
                        fontSize = 20.sp,
                        color = AppTheme.colors.onPrimary
                    )
                }
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
    category: NewsCategory,
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
        item(key = category) {
            NewsListHeader(
                modifier = Modifier.widthIn(max = maxContentWidth),
                category = category
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