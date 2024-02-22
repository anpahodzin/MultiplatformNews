import ComposeApp
import SwiftUI

final class RootHolder {
  let lifecycle: LifecycleRegistry
  let root: RootComponent
  
  init() {
    KoinKt.doInitKoin()
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
