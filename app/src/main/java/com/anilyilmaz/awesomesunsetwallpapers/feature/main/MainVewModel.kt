package com.anilyilmaz.awesomesunsetwallpapers.feature.main

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVewModel @Inject constructor(val connectivityManager: ConnectivityManager): ViewModel() {
    fun getNetworkState(): NetworkState {
        val networkState = if(connectivityManager.activeNetwork != null) NetworkState.AVAILABLE
        else NetworkState.UNAVAILABLE

        return networkState
    }
}