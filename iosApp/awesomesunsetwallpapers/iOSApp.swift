import SwiftUI
import composeApp

@main
struct AwesomeSunsetWallpapersApp: App {
    private let networkMonitor = iOSNativeNetworkMonitor()

    init() {
        networkMonitor.start()
    }
    
    var body: some Scene {
        WindowGroup {
            RootView()
                .ignoresSafeArea()
        }
    }
}

struct RootView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
}
