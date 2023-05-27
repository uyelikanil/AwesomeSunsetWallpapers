package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.anilyilmaz.awesomesunsetwallpapers.core.common.Result
import com.anilyilmaz.awesomesunsetwallpapers.core.common.asResult
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

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getWallpapers() = getPhotoUseCase.getPhotos(listOf("sunset"), 30)
        .cachedIn(viewModelScope)
        .asResult()
        .map { wallpaperResult ->
            when(wallpaperResult) {
                is Result.Success -> {
                    _uiState.update { it.copy(pagingData = wallpaperResult.data, isLoading = false) }
                }

                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Result.Error -> {
                    _uiState.update { it.copy(error = true, isLoading = false) }
                }
            }
        }
        .launchIn(viewModelScope)

    fun errorHandled() {
        _uiState.update { it.copy(error = false) }
    }
}