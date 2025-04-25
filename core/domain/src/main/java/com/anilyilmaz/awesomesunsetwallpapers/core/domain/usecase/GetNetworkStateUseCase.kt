package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState

class GetNetworkStateUseCase {
    operator fun invoke(newState: NetworkState, oldState: NetworkState): NetworkState {
        val state = if(newState == NetworkState.AVAILABLE &&
            (oldState == NetworkState.LOST || oldState == NetworkState.UNAVAILABLE)) {
            NetworkState.CONNECTED
        } else {
            newState
        }
        return state
    }

    operator fun invoke(isThereActiveNetwork: Boolean): NetworkState {
        val networkState = if(isThereActiveNetwork) NetworkState.AVAILABLE
        else NetworkState.UNAVAILABLE
        return networkState
    }
}