import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import extension.getAsyncImageLoader
import root.component.RootComponent
import theme.AppTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(component: RootComponent) {
    CompositionLocalProvider(
        LocalLifecycleOwner provides component,
    ) {

        setSingletonImageLoaderFactory { context ->
            getAsyncImageLoader(context)
        }

        AppTheme {
            RootScreen(component = component)
        }
    }
}

internal val LocalLifecycleOwner = staticCompositionLocalOf<LifecycleOwner> {
    error("CompositionLocal LocalLifecycleOwner not present")
}