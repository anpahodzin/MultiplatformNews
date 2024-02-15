package news.detailed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import extension.pxToDp
import theme.AppTheme

@Composable
fun NewsDetailedScreen(component: NewsDetailedComponent) {
    val news by component.state.subscribeAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        var toolbarSize by remember { mutableStateOf(IntSize.Zero) }
        NewsToolbar(
            modifier = Modifier
                .widthIn(max = AppTheme.sizes.maxContentWidth)
                .align(Alignment.TopCenter)
                .onSizeChanged { toolbarSize = it },
            onBackClick = { component.onBackPressed() },
            title = news.sourceName,
        )

        NewsDetailedContent(
            modifier = Modifier
                .safeDrawingPadding()
                .padding(top = toolbarSize.height.pxToDp())
                .align(Alignment.TopCenter)
                .widthIn(max = AppTheme.sizes.maxContentWidth),
            news = news,
        )
    }
}