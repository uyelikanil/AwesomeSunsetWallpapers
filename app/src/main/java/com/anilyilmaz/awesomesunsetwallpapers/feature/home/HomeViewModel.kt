package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPhotoUseCase: GetPhotoUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getWallpapers()
    }

    fun getWallpapers() = getPhotoUseCase.getPhotos(listOf("sunset"), 30)
        .cachedIn(viewModelScope)
        .map { wallpaperResult -> _uiState.update { it.copy(wallpaperResult)} }
        .launchIn(viewModelScope)
}