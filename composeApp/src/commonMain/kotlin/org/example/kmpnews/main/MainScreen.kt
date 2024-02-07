package org.example.kmpnews.main

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import main.component.MainComponent
import org.example.kmpnews.news.list.NewsListScreen
import org.example.kmpnews.theme.AppTheme

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
            is MainComponent.Child.NewsList -> NewsListScreen(component = child.component)
        }
    }
}