package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState

interface GetNetworkStateUseCase {

    fun getState(newState: NetworkState, oldState: NetworkState): NetworkState

    fun getState(): NetworkState

}