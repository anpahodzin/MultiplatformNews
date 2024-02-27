package news.detailed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import extension.collectSideEffect
import extension.pxToDp
import kotlinx.coroutines.launch
import news.detailed.mapper.toMessage
import theme.AppTheme
import view.CustomSnackbar

@Composable
fun NewsDetailedScreen(component: NewsDetailedComponent) {
    val componentState by component.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    when (val state = componentState) {
        is NewsDetailedUiState.Data -> {
            Box(modifier = Modifier.fillMaxSize()) {
                var toolbarSize by remember { mutableStateOf(IntSize.Zero) }
                NewsToolbar(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .widthIn(max = AppTheme.sizes.maxContentWidth)
                        .align(Alignment.TopCenter)
                        .onSizeChanged { toolbarSize = it },
                    onBackClick = { component.onBackPressed() },
                    onFavoriteClick = { component.onFavoritePressed() },
                    isFavoriteFilled = state.isFavoriteNews,
                    title = state.news.sourceName,
                )

                NewsDetailedContent(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .safeDrawingPadding()
                        .padding(top = toolbarSize.height.pxToDp())
                        .align(Alignment.TopCenter)
                        .widthIn(max = AppTheme.sizes.maxContentWidth),
                    news = state.news,
                )

                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .safeDrawingPadding(),
                    snackbar = { CustomSnackbar(it) }
                )
            }
        }

        NewsDetailedUiState.Error -> {}
        NewsDetailedUiState.Loading -> {}
    }

    component.eventChannel.collectSideEffect { event ->
        snackbarHostState.currentSnackbarData?.dismiss()
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message = event.toMessage())
        }
    }
}