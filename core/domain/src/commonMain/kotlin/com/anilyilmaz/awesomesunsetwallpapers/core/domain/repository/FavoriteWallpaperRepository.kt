package com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow

interface FavoriteWallpaperRepository {
    fun observeFavorites(): Flow<List<Photo>>

    fun observeFavoriteIds(): Flow<Set<Long>>

    fun observeIsFavorite(id: Long): Flow<Boolean>

    suspend fun getFavorite(id: Long): Photo?

    suspend fun toggleFavorite(photo: Photo)
}
