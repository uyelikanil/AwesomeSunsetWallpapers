package com.anilyilmaz.awesomesunsetwallpapers.core.system

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidNetworkMonitor(
    private val connectivityManager: ConnectivityManager
) : NetworkMonitor {

    private val _state = MutableStateFlow(currentState())
    override val state: StateFlow<NetworkState> = _state.asStateFlow()

    private val networkRequest: NetworkRequest =
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            updateState()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            updateState()
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            updateState()
        }
    }

    init {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun updateState() {
        _state.value = currentState()
    }

    private fun currentState(): NetworkState {
        val activeNetwork = connectivityManager.activeNetwork ?: return NetworkState.UNAVAILABLE
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            ?: return NetworkState.UNAVAILABLE

        val hasInternet =
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        return if (hasInternet) NetworkState.AVAILABLE else NetworkState.UNAVAILABLE
    }
}