import ComposeApp
import SwiftUI

final class RootHolder {
  let lifecycle: LifecycleRegistry
  let root: RootComponent
  
  init() {
    lifecycle = LifecycleRegistryKt.LifecycleRegistry()
    root = RootDefaultComponent(
      componentContext: DefaultComponentContext(lifecycle: lifecycle)
    )
    lifecycle.onCreate()
  }
  
  deinit {
    lifecycle.onDestroy()
  }
}
