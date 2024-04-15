package news.eveything

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
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
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import extension.pxToDp
import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.everything
import multiplatformnews.composeapp.generated.resources.something_went_wrong
import multiplatformnews.composeapp.generated.resources.try_again
import news.NewsCard
import news.NewsHeader
import news.everything.NewsEverythingComponent
import news.everything.NewsEverythingUiState
import news.everything.getNewsOrNull
import news.model.News
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun NewsEverythingScreen(
    component: NewsEverythingComponent,
    bottomPadding: Dp
) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        var categoryBarSize by remember { mutableStateOf(IntSize.Zero) }
        val imeBottomPadding = WindowInsets.ime.asPaddingValues().calculateBottomPadding()
        val correctBottomPadding = max(bottomPadding, imeBottomPadding)
        val lazyListState = rememberLazyListState()

        componentState.getNewsOrNull()?.let { newsList ->
            NewsListContent(
                newsList = newsList,
                onNewsSelected = component::onNewsSelected,
                bottomPadding = categoryBarSize.height.pxToDp() + correctBottomPadding,
                lazyListState = lazyListState
            )
        }

        NewsSearchBar(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(horizontal = AppTheme.sizes.medium)
                .padding(bottom = correctBottomPadding)
                .onSizeChanged { categoryBarSize = it }
                .padding(vertical = AppTheme.sizes.medium),
            value = componentState.search,
            onValueChange = component::onSearchQueryChanged,
        )

        when (val state = componentState) {
            is NewsEverythingUiState.Data -> {
                LaunchedEffect(state) {
                    lazyListState.scrollToItem(0)
                }
            }

            is NewsEverythingUiState.Error -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .safeDrawingPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(Res.string.something_went_wrong), fontSize = 20.sp)
                    Text(
                        modifier = Modifier
                            .padding(AppTheme.sizes.medium)
                            .clip(AppTheme.shapes.circle)
                            .background(AppTheme.colors.primary)
                            .clickable { component.refresh() }
                            .padding(AppTheme.sizes.medium),
                        text = stringResource(Res.string.try_again),
                        fontSize = 20.sp,
                        color = AppTheme.colors.onPrimary
                    )
                }
            }

            is NewsEverythingUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = AppTheme.colors.secondary
                )
            }

            is NewsEverythingUiState.Empty -> {

            }
        }
    }
}

@Composable
private fun NewsListContent(
    newsList: List<News>,
    onNewsSelected: (News) -> Unit,
    bottomPadding: Dp,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val maxContentWidth = AppTheme.sizes.maxContentWidth
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = topPadding,
            bottom = bottomPadding
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = lazyListState,
    ) {
        item {
            NewsHeader(
                modifier = Modifier.widthIn(max = maxContentWidth),
                title = stringResource(Res.string.everything),
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