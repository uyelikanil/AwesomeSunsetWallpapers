import Network
import composeApp

final class iOSNativeNetworkMonitor {
    private let monitor = NWPathMonitor()
    private let queue = DispatchQueue(label: "NetworkMonitor")

    func start() {
        monitor.pathUpdateHandler = { path in
            let isConnected = (path.status == .satisfied)
            IosNetworkMonitor.shared.onNetworkChanged(isConnected: isConnected)
        }

        monitor.start(queue: queue)
    }

    deinit {
        monitor.cancel()
    }
}
