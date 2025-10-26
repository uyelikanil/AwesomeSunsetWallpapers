package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WallpaperDetailViewModel(
    private val wallpaperId: Long,
    private val photoRepository: PhotoRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<WallpaperDetailUiState>(WallpaperDetailUiState.Loading)
    val uiState: StateFlow<WallpaperDetailUiState> = _uiState.asStateFlow()

    init {
        getWallpaper()
    }

    fun getWallpaper() = viewModelScope.launch {
        _uiState.value = WallpaperDetailUiState.Loading

        try {
            val photo = photoRepository.getPhoto(wallpaperId)
            _uiState.value = WallpaperDetailUiState.Success(
                wallpaperSrc = photo.src.portrait,
                photographer = photo.photographer
            )
        } catch (e: Exception) {
            _uiState.value = WallpaperDetailUiState.Error
        }
    }
}