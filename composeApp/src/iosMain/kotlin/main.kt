import androidx.compose.ui.window.ComposeUIViewController
import org.example.kmpnews.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
