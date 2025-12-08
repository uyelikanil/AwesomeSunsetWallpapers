package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

sealed interface WallpaperDetailCapabilityState {
    object Init : WallpaperDetailCapabilityState

    object Success : WallpaperDetailCapabilityState

    object Loading : WallpaperDetailCapabilityState

    data class Error(val message: String? = null) : WallpaperDetailCapabilityState
}