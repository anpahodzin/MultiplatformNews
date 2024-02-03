import androidx.compose.ui.window.ComposeUIViewController
import org.example.kmpnews.App
import platform.UIKit.UIViewController
import root.component.RootComponent

fun MainViewController(component: RootComponent): UIViewController = ComposeUIViewController { App(component) }
