import androidx.compose.runtime.Composable
import theme.AppTheme
import root.component.RootComponent

@Composable
fun App(component: RootComponent) {
    AppTheme {
        RootScreen(component = component)
    }
}