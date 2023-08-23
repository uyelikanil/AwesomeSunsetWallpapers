package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import javax.inject.Inject

class GetNetworkStateUseCaseImpl @Inject constructor(): GetNetworkStateUseCase {

    override fun getState(newState: NetworkState, oldState: NetworkState): NetworkState {
        val state = if(newState == NetworkState.AVAILABLE &&
            (oldState == NetworkState.LOST || oldState == NetworkState.UNAVAILABLE)) {
            NetworkState.CONNECTED
        } else {
            newState
        }
        return state
    }

    override fun getState(isThereActiveNetwork: Boolean): NetworkState {
        val networkState = if(isThereActiveNetwork) NetworkState.AVAILABLE
        else NetworkState.UNAVAILABLE
        return networkState
    }
}