import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import root.component.RootComponent
import theme.AppTheme

@Composable
fun App(component: RootComponent) {
    CompositionLocalProvider(
        LocalLifecycleOwner provides component,
    ) {
        AppTheme {
            RootScreen(component = component)
        }
    }
}

internal val LocalLifecycleOwner = staticCompositionLocalOf<LifecycleOwner> {
    error("CompositionLocal LocalLifecycleOwner not present")
}