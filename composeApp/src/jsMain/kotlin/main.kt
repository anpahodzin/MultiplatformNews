import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import helper.initCommon
import org.jetbrains.skiko.wasm.onWasmReady
import root.component.RootDefaultComponent

@JsName("mainJs")
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initCommon()
    val lifecycle = LifecycleRegistry()
    val root = RootDefaultComponent(DefaultComponentContext(lifecycle))
    onWasmReady {
        CanvasBasedWindow("KmpApp") {
            App(root)
        }
    }
}
