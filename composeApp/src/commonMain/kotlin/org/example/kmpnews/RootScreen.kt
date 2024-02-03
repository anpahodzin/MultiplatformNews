package org.example.kmpnews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import main.MainScreen
import org.example.kmpnews.main.MainScreen
import root.component.RootComponent

@Composable
internal fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.subscribeAsState()
    Children(
        stack = childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Main -> MainScreen(component = child.component)
        }
    }
}