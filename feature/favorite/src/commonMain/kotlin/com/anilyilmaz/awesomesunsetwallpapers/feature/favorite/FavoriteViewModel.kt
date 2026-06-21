package com.anilyilmaz.awesomesunsetwallpapers.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteWallpaperRepository: FavoriteWallpaperRepository,
) : ViewModel() {
    val favoriteWallpapers = favoriteWallpaperRepository.observeFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    fun toggleFavorite(photo: Photo) {
        viewModelScope.launch {
            favoriteWallpaperRepository.toggleFavorite(photo)
        }
    }
}
