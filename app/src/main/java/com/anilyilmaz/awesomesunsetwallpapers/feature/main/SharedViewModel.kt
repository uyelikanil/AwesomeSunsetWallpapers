package com.anilyilmaz.awesomesunsetwallpapers.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {
    private val _networkState = MutableLiveData(NetworkState.AVAILABLE)
    val networkState: LiveData<NetworkState> = _networkState

    fun updateNetworkState(state: NetworkState) = viewModelScope.launch {
        if(state == NetworkState.AVAILABLE &&
            (networkState.value == NetworkState.LOST ||
                    networkState.value == NetworkState.UNAVAILABLE)) {
            _networkState.value = NetworkState.CONNECTED
        } else {
            _networkState.value = state
        }
    }
}