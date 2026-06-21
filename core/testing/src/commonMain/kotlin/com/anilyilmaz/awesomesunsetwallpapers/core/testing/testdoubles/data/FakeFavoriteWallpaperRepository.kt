package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.data

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeFavoriteWallpaperRepository(
    initialFavorites: List<Photo> = emptyList(),
) : FavoriteWallpaperRepository {
    private val favorites = MutableStateFlow(
        initialFavorites.map { photo -> photo.copy(isFavorite = true) }
    )

    override fun observeFavorites(): Flow<List<Photo>> = favorites

    override fun observeFavoriteIds(): Flow<Set<Long>> =
        favorites.map { photos -> photos.mapTo(mutableSetOf(), Photo::id) }

    override fun observeIsFavorite(id: Long): Flow<Boolean> =
        favorites.map { photos -> photos.any { it.id == id } }

    override suspend fun getFavorite(id: Long): Photo? =
        favorites.value.firstOrNull { it.id == id }

    override suspend fun toggleFavorite(photo: Photo) {
        favorites.update { currentFavorites ->
            if (currentFavorites.any { it.id == photo.id }) {
                currentFavorites.filterNot { it.id == photo.id }
            } else {
                listOf(photo.copy(isFavorite = true)) + currentFavorites
            }
        }
    }
}
