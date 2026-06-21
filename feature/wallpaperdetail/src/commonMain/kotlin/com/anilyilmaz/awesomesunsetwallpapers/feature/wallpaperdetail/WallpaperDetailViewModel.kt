package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetWallpaperUseCase
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapability
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapabilityResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WallpaperDetailViewModel(
    private val wallpaperId: Long,
    private val getWallpaperUseCase: GetWallpaperUseCase,
    private val favoriteWallpaperRepository: FavoriteWallpaperRepository,
    private val capability: WallpaperCapability,
    private val loadOnInit: Boolean = true
) : ViewModel() {
    private val _uiState = MutableStateFlow<WallpaperDetailUiState>(WallpaperDetailUiState.Loading)
    val uiState: StateFlow<WallpaperDetailUiState> = _uiState.asStateFlow()

    private val _capabilityState = MutableStateFlow<WallpaperDetailCapabilityState>(
        WallpaperDetailCapabilityState.Init
    )
    val capabilityState: StateFlow<WallpaperDetailCapabilityState> = _capabilityState.asStateFlow()

    init {
        if(loadOnInit) {
            getWallpaper()
        }
    }

    fun onWallpaperActionHandled() {
        _capabilityState.update { WallpaperDetailCapabilityState.Init }
    }

    fun getWallpaper() = viewModelScope.launch {
        _uiState.value = WallpaperDetailUiState.Loading

        try {
            val photo = getWallpaperUseCase(wallpaperId)
            _uiState.value = WallpaperDetailUiState.Success(photo)
        } catch (e: Exception) {
            _uiState.value = WallpaperDetailUiState.Error
        }
    }

    fun onWallpaperAction() = viewModelScope.launch {
        if (uiState.value is WallpaperDetailUiState.Success) {
            _capabilityState.update { WallpaperDetailCapabilityState.Loading }
            val result = capability.performPrimaryAction(
                (uiState.value as WallpaperDetailUiState.Success).photo.src.portrait
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

    fun toggleFavorite() {
        val photo = (uiState.value as? WallpaperDetailUiState.Success)?.photo ?: return
        viewModelScope.launch {
            favoriteWallpaperRepository.toggleFavorite(photo)
            _uiState.update { state ->
                if (state is WallpaperDetailUiState.Success) {
                    state.copy(photo = state.photo.copy(isFavorite = !photo.isFavorite))
                } else {
                    state
                }
            }
        }
    }
}
