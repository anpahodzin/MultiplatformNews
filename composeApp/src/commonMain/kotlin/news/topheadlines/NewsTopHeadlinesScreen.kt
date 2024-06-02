package news.topheadlines

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import extension.pxToDp
import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.ic_refresh_24
import multiplatformnews.composeapp.generated.resources.retry
import multiplatformnews.composeapp.generated.resources.top_headlines
import news.NewsCard
import news.NewsHeader
import news.model.News
import news.model.NewsCategory
import news.topheadlines.category.NewsCategoryBar
import news.topheadlines.category.toLocalizedString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import view.AppButtonRow
import view.shimmer.ShimmerBox

@Composable
fun NewsTopHeadlinesScreen(
    component: NewsTopHeadlinesComponent,
    bottomPadding: Dp
) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        var categoryBarSize by remember { mutableStateOf(IntSize.Zero) }
        val lazyListState = rememberLazyListState()

        val pagingItems = componentState.pagingData.collectAsLazyPagingItems()

        LaunchedEffect(componentState.selectedCategory) {
            lazyListState.scrollToItem(0)
        }

        NewsTopHeadlinesContent(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight()
                .widthIn(max = AppTheme.sizes.maxContentWidth),
            pagingItems = pagingItems,
            category = componentState.selectedCategory,
            onNewsSelected = component::onNewsSelected,
            bottomPadding = categoryBarSize.height.pxToDp() + bottomPadding,
            lazyListState = lazyListState,
        )

        NewsCategoryBar(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = bottomPadding + AppTheme.sizes.small)
                .onSizeChanged { categoryBarSize = it },
            selectedNewsCategory = componentState.selectedCategory,
            onCategoryClick = component::onCategorySelected
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NewsTopHeadlinesContent(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<News>,
    category: NewsCategory,
    onNewsSelected: (News) -> Unit,
    bottomPadding: Dp,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val insets = WindowInsets.safeDrawing.asPaddingValues()
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = insets.calculateTopPadding(),
            bottom = insets.calculateBottomPadding() + bottomPadding
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState,
        userScrollEnabled = pagingItems.loadState.refresh is LoadStateNotLoading,
    ) {
        item(key = category) {
            NewsHeader(
                title = stringResource(Res.string.top_headlines),
                subTitle = category.toLocalizedString()
            )
        }

        when (val loadState = pagingItems.loadState.refresh) {
            LoadStateLoading -> {
                items(10) {
                    ShimmerBox(
                        modifier = Modifier
                            .animateItemPlacement()
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(
                                horizontal = AppTheme.sizes.medium,
                                vertical = AppTheme.sizes.small
                            )
                    )
                }
            }

            is LoadStateNotLoading -> {
                items(pagingItems.itemCount) { index ->
                    val item = pagingItems[index]
                    if (item != null) {
                        NewsCard(
                            modifier = Modifier.animateItemPlacement(),
                            news = item,
                            onNewsSelected = onNewsSelected
                        )
                    } else {
                        ShimmerBox(
                            modifier = Modifier
                                .animateItemPlacement()
                                .fillMaxWidth()
                                .height(160.dp)
                                .padding(
                                    horizontal = AppTheme.sizes.medium,
                                    vertical = AppTheme.sizes.small
                                )
                        )
                    }
                }
            }

            is LoadStateError -> {
                item {
                    AppButtonRow(
                        modifier = Modifier.padding(
                            horizontal = AppTheme.sizes.medium,
                            vertical = AppTheme.sizes.small
                        ),
                        elevation = AppTheme.sizes.elevationSmall,
                        onClick = { pagingItems.retry() }
                    ) { _, color ->
                        val text = stringResource(Res.string.retry)
                        Text(
                            text = text,
                            color = color,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.size(AppTheme.sizes.small))
                        Image(
                            painter = painterResource(Res.drawable.ic_refresh_24),
                            contentDescription = text,
                            colorFilter = ColorFilter.tint(color)
                        )
                    }
                }
            }

            else -> error("when should be exhaustive")
        }
    }
}