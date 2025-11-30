package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

sealed interface WallpaperCapabilityResult {
    object Success : WallpaperCapabilityResult
    data class Error(val message: String? = null) : WallpaperCapabilityResult
}