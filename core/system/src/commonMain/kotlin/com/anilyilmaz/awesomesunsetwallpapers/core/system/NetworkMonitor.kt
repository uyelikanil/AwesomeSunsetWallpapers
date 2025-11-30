package com.anilyilmaz.awesomesunsetwallpapers.core.system

import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val state: StateFlow<NetworkState>
}