package com.anilyilmaz.awesomesunsetwallpapers.feature.main.testdouble

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.core.system.NetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeNetworkMonitor(
    initial: NetworkState = NetworkState.AVAILABLE
) : NetworkMonitor {

    private val _state = MutableStateFlow(initial)
    override val state: StateFlow<NetworkState> = _state.asStateFlow()

    fun emit(state: NetworkState) {
        _state.value = state
    }
}