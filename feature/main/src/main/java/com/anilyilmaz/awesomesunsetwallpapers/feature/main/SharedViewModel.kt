package com.anilyilmaz.awesomesunsetwallpapers.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val getNetworkStateUseCase:
                                          GetNetworkStateUseCase): ViewModel() {
    private var currentNetworkState = NetworkState.AVAILABLE
    private val _networkState = MutableSharedFlow<NetworkState>()
    val networkState: SharedFlow<NetworkState> = _networkState

    fun updateNetworkState(newNetworkState: NetworkState) = viewModelScope.launch {
        val state = getNetworkStateUseCase.getState(newNetworkState, currentNetworkState)
        currentNetworkState = state
        _networkState.emit(state)
    }

    fun updateNetworkState(isThereActiveNetwork: Boolean) = viewModelScope.launch {
        val state = getNetworkStateUseCase.getState(isThereActiveNetwork)
        updateNetworkState(state)
    }
}