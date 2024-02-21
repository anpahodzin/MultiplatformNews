import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import helper.initCommon
import multiplatformnews.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import root.component.RootDefaultComponent
import java.awt.Dimension

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    initCommon()
    val lifecycle = LifecycleRegistry()
    val root = RootDefaultComponent(DefaultComponentContext(lifecycle))
    application {
        val windowState = rememberWindowState(width = 1200.dp, height = 1000.dp)
        // Bind the registry to the lifecycle of the window
        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = stringResource(Res.string.app_name)
        ) {
            window.minimumSize = Dimension(400, 600)
            App(root)
        }
    }
}