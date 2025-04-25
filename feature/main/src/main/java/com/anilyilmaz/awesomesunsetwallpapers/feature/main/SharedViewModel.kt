package com.anilyilmaz.awesomesunsetwallpapers.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val getNetworkStateUseCase: GetNetworkStateUseCase
): ViewModel() {
    private var currentNetworkState = NetworkState.AVAILABLE
    private val _networkState = MutableSharedFlow<NetworkState>()
    val networkState: SharedFlow<NetworkState> = _networkState

    fun updateNetworkState(newNetworkState: NetworkState) = viewModelScope.launch {
        val state = getNetworkStateUseCase(newNetworkState, currentNetworkState)
        currentNetworkState = state
        _networkState.emit(state)
    }

    fun updateNetworkState(isThereActiveNetwork: Boolean) = viewModelScope.launch {
        val state = getNetworkStateUseCase(isThereActiveNetwork)
        updateNetworkState(state)
    }
}