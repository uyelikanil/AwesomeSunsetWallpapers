import SwiftUI
import composeApp

@main
struct iOSApp: App {
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
