package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.LoadMoreSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getSunsetPhotosUseCase: GetSunsetPhotosUseCase,
    private val loadMoreSunsetPhotosUseCase: LoadMoreSunsetPhotosUseCase,
    private val favoriteWallpaperRepository: FavoriteWallpaperRepository,
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
        observeFavoriteIds()
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
            val photoExpanded = getSunsetPhotosUseCase()
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
        val currentState = uiState.value
        val currentPage = currentState.photoExpanded ?: return@launch

        val isRefreshComplete = currentState.loadState.refresh == LoadStatus.NotLoading
        val isLoadingMore = currentState.loadState.append == LoadStatus.Loading

        if (!isRefreshComplete || isLoadingMore) return@launch

        updateAppendStatus(LoadStatus.Loading)

        try {
            val nextPage = loadMoreSunsetPhotosUseCase(
                page = currentPage.page,
                perPage = currentPage.perPage,
                totalResults = currentPage.totalResults
            )

            if (nextPage == null) {
                updateAppendStatus(LoadStatus.Error)
                return@launch
            }

            val latestPage = uiState.value.photoExpanded ?: return@launch
            val uniquePhotos = (latestPage.photos + nextPage.photos)
                .distinctBy { photo -> photo.id }

            _uiState.update { state ->
                state.copy(
                    photoExpanded = nextPage.copy(photos = uniquePhotos),
                    loadState = state.loadState.copy(
                        append = LoadStatus.NotLoading
                    )
                )
            }
        } catch (_: Exception) {
            updateAppendStatus(LoadStatus.Error)
        }
    }

    fun toggleFavorite(photo: Photo) {
        viewModelScope.launch {
            favoriteWallpaperRepository.toggleFavorite(photo)
        }
    }

    private fun observeFavoriteIds() {
        viewModelScope.launch {
            favoriteWallpaperRepository.observeFavoriteIds()
                .collect { favoriteIds -> updateFavoriteInfo(favoriteIds) }
        }
    }

    private fun updateFavoriteInfo(favoriteIds: Set<Long>) {
        _uiState.update { state ->
            state.copy(
                photoExpanded = state.photoExpanded?.withFavoriteInfo(favoriteIds)
            )
        }
    }

    private fun updateAppendStatus(status: LoadStatus) {
        _uiState.update { state ->
            state.copy(
                loadState = state.loadState.copy(
                    append = status
                )
            )
        }
    }
}

private fun PhotoExpanded.withFavoriteInfo(favoriteIds: Set<Long>): PhotoExpanded =
    copy(
        photos = photos.map { photo -> photo.copy(isFavorite = photo.id in favoriteIds) }
    )
