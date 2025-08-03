package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.LoadMoreSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val getSunsetPhotosUpdatedUseCase: GetSunsetPhotosUseCase,
    val loadMoreSunsetPhotosUseCase: LoadMoreSunsetPhotosUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            loadState = LoadState(
                refresh = LoadStatus.Loading,
                append = LoadStatus.NotLoading
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getPhotos()
    }

    fun getPhotos() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                photoExpanded = null,
                loadState = LoadState(
                    refresh = LoadStatus.Loading,
                    append = LoadStatus.NotLoading
                )
            )
        }
        try {
            val photoExpanded = getSunsetPhotosUpdatedUseCase()
            _uiState.update {
                it.copy(
                    photoExpanded = photoExpanded,
                    loadState = LoadState(
                        refresh = LoadStatus.NotLoading,
                        append = LoadStatus.NotLoading
                    )
                )
            }
        } catch (_: Exception) {
            _uiState.update {
                it.copy(
                    photoExpanded = null,
                    loadState = LoadState(
                        refresh = LoadStatus.Error,
                        append = LoadStatus.NotLoading
                    )
                )
            }
        }
    }

    fun loadMorePhotos() = viewModelScope.launch {
        uiState.value.photoExpanded?.run {
            if (uiState.value.loadState.refresh == LoadStatus.NotLoading) {
                _uiState.update {
                    it.copy(
                        loadState = it.loadState.copy(
                            append = LoadStatus.Loading
                        )
                    )
                }
                try {
                    val response = loadMoreSunsetPhotosUseCase(
                        page = page,
                        perPage = perPage,
                        totalResults = totalResults
                    )

                    if (response != null) {
                        val mutableList = photos.toMutableList()
                        mutableList.addAll(response.photos)
                        val updatedList = mutableList.toList()

                        val updatedPhotoExpanded = copy(
                            totalResults = response.totalResults,
                            page = response.page,
                            perPage = response.perPage,
                            photos = updatedList
                        )

                        _uiState.update { uiState ->
                            uiState.copy(
                                photoExpanded = updatedPhotoExpanded,
                                loadState = uiState.loadState.copy(
                                    append = LoadStatus.NotLoading
                                )
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                loadState = it.loadState.copy(
                                    append = LoadStatus.Error
                                )
                            )
                        }
                    }
                } catch (_: Exception) {
                    _uiState.update {
                        it.copy(
                            loadState = it.loadState.copy(
                                append = LoadStatus.Error
                            )
                        )
                    }
                }
            }
        }
    }
}