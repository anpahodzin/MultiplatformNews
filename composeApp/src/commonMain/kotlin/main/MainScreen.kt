package main

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import main.component.MainComponent
import news.detailed.NewsDetailedScreen
import news.tabs.NewsTabsScreen
import theme.AppTheme

@Composable
fun MainScreen(
    component: MainComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.subscribeAsState()
    Children(
        stack = childStack,
        modifier = modifier.background(AppTheme.colors.background)
    ) {
        when (val child = it.instance) {
            is MainComponent.Child.NewsTabs -> NewsTabsScreen(child.component)
            is MainComponent.Child.NewsDetailed -> NewsDetailedScreen(child.component)
        }
    }
}