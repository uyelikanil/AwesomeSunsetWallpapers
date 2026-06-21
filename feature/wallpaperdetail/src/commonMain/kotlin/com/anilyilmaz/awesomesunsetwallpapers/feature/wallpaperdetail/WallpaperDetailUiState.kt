package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo

sealed interface WallpaperDetailUiState {
    data class Success(
        val photo: Photo,
    ): WallpaperDetailUiState

    object Loading: WallpaperDetailUiState

    object Error: WallpaperDetailUiState
}
