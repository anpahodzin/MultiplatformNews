package news.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import multiplatformnews.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun NewsFavoriteScreen(
    component: NewsFavoriteComponent,
    bottomPadding: Dp
) {
    val componentState by component.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        val lazyListState = rememberLazyListState()

        when (val state = componentState) {
            is NewsFavoriteUiState.Data -> {
                NewsFavoriteListContent(
                    newsList = state.favoriteNews,
                    onNewsSelected = { component.onNewsSelected(it) },
                    bottomPadding = bottomPadding,
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