package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapability
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapabilityResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WallpaperDetailViewModel(
    private val wallpaperId: Long,
    private val photoRepository: PhotoRepository,
    private val capability: WallpaperCapability,
) : ViewModel() {
    private val _uiState = MutableStateFlow<WallpaperDetailUiState>(WallpaperDetailUiState.Loading)
    val uiState: StateFlow<WallpaperDetailUiState> = _uiState.asStateFlow()

    private val _capabilityState = MutableStateFlow<WallpaperDetailCapabilityState>(
        WallpaperDetailCapabilityState.Init
    )
    val capabilityState: StateFlow<WallpaperDetailCapabilityState> = _capabilityState.asStateFlow()

    init {

    }

    fun onWallpaperActionHandled() {
        _capabilityState.update { WallpaperDetailCapabilityState.Init }
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

    fun onWallpaperAction() = viewModelScope.launch {
        if (uiState.value is WallpaperDetailUiState.Success) {
            _capabilityState.update { WallpaperDetailCapabilityState.Loading }
            val result = capability.performPrimaryAction(
                (uiState.value as WallpaperDetailUiState.Success).wallpaperSrc
            )
            when (result) {
                is WallpaperCapabilityResult.Success -> {
                    _capabilityState.update { WallpaperDetailCapabilityState.Success }
                }

                is WallpaperCapabilityResult.Error -> {
                    _capabilityState.update { WallpaperDetailCapabilityState.Error() }
                }
            }
        }
    }
}