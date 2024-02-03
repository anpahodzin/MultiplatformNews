package org.example.kmpnews

import androidx.compose.runtime.Composable
import org.example.kmpnews.theme.AppTheme
import root.component.RootComponent

@Composable
fun App(component: RootComponent) {
    AppTheme {
        RootScreen(component = component)
    }
}