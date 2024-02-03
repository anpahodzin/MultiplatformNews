import UIKit
import SwiftUI
import ComposeApp

@main
struct iosApp: App {
    let rootHolder = RootHolder()
    
    @Environment(\.scenePhase)
    var scenePhase: ScenePhase
    
    var body: some Scene {
        WindowGroup {
            ContentView(component: rootHolder.root)
                .onChange(of: scenePhase) { newPhase in
                    switch newPhase {
                    case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
                    case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
                    case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
                    @unknown default: break
                    }
                }
        }
    }
}

struct ContentView: View {
    let root: RootComponent
    
    init(component: RootComponent) {
        self.root = component
    }
    
    var body: some View {
        ComposeView(component: root).ignoresSafeArea(.all)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    let root: RootComponent
    
    init(component: RootComponent) {
        self.root = component
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainKt.MainViewController(component: root)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
