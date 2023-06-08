package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

sealed interface WallpaperDetailUiState {
    data class Success(val wallpaperSrc: String = "",
        val photographer: String = ""): WallpaperDetailUiState

    object Loading: WallpaperDetailUiState

    object Error: WallpaperDetailUiState
}
