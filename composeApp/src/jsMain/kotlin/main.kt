import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import di.initKoin
import org.jetbrains.skiko.wasm.onWasmReady
import root.component.RootDefaultComponent

@JsName("mainJs")
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    val lifecycle = LifecycleRegistry()
    val root = RootDefaultComponent(DefaultComponentContext(lifecycle))
    onWasmReady {
        CanvasBasedWindow("MultiplatformNews") {
            App(root)
        }
    }
}
