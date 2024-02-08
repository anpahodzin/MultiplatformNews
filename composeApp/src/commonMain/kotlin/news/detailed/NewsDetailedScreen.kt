package news.detailed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun NewsDetailedScreen(component: NewsDetailedComponent) {
    val news by component.state.subscribeAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Text(news.title, modifier = Modifier.align(Alignment.Center))
    }
}