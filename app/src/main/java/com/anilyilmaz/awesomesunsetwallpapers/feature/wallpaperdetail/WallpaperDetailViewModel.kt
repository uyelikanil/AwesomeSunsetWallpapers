package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpaperDetailViewModel @Inject constructor(private val getPhotoUseCase: GetPhotoUseCase):
    ViewModel() {

    private val _uiState = MutableStateFlow<WallpaperDetailUiState>(WallpaperDetailUiState.Loading)
    val uiState: StateFlow<WallpaperDetailUiState> = _uiState.asStateFlow()

    fun getWallpaper(id: Int) = viewModelScope.launch {
        _uiState.value = WallpaperDetailUiState.Loading

        try {
            val photo = getPhotoUseCase.getPhoto(id)
            _uiState.value = WallpaperDetailUiState.Success(photo.src.portrait, photo.photographer)
        } catch (e: Exception) {
            _uiState.value = WallpaperDetailUiState.Error
        }
    }
}