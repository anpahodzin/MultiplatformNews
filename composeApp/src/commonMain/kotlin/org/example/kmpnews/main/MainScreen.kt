package org.example.kmpnews.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import main.component.MainComponent

@Composable
fun MainScreen(
    component: MainComponent,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("MainScreen", modifier = Modifier.align(Alignment.Center))
    }
}