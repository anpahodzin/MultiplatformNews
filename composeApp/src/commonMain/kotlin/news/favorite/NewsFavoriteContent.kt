package news.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import multiplatformnews.composeapp.generated.resources.Res
import multiplatformnews.composeapp.generated.resources.favorite
import news.NewsCard
import news.NewsHeader
import news.model.News
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun NewsFavoriteListContent(
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
            NewsHeader(
                modifier = Modifier.widthIn(max = maxContentWidth),
                title = stringResource(Res.string.favorite),
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