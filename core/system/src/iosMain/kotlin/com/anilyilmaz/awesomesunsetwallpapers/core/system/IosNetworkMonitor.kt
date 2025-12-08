package com.anilyilmaz.awesomesunsetwallpapers.core.system

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object IosNetworkMonitor : NetworkMonitor {

    private val _state = MutableStateFlow(NetworkState.UNAVAILABLE)
    override val state: StateFlow<NetworkState> = _state.asStateFlow()

    fun onNetworkChanged(isConnected: Boolean) {
        _state.value = if (isConnected) {
            NetworkState.AVAILABLE
        } else {
            NetworkState.UNAVAILABLE
        }
    }
}