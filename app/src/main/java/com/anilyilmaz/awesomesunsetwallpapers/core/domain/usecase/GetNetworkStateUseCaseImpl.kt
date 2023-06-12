package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import android.net.ConnectivityManager
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import javax.inject.Inject

class GetNetworkStateUseCaseImpl @Inject constructor(val connectivityManager: ConnectivityManager):
    GetNetworkStateUseCase {

    override fun getState(newState: NetworkState, oldState: NetworkState): NetworkState {
        val state = if(newState == NetworkState.AVAILABLE &&
            (oldState == NetworkState.LOST || oldState == NetworkState.UNAVAILABLE)) {
            NetworkState.CONNECTED
        } else {
            newState
        }
        return state
    }

    override fun getState(): NetworkState {
        val networkState = if(connectivityManager.activeNetwork != null) NetworkState.AVAILABLE
        else NetworkState.UNAVAILABLE
        return networkState
    }
}