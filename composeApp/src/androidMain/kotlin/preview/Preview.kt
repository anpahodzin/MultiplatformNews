package preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Preview() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Hello", modifier = Modifier.align(Alignment.Center))
    }
}