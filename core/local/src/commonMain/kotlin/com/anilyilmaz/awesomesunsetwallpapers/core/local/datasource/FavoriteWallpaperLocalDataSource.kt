package com.anilyilmaz.awesomesunsetwallpapers.core.local.datasource

import com.anilyilmaz.awesomesunsetwallpapers.core.local.model.FavoriteWallpaper
import kotlinx.coroutines.flow.Flow

interface FavoriteWallpaperLocalDataSource {
    fun observeFavorites(): Flow<List<FavoriteWallpaper>>

    fun observeFavoriteIds(): Flow<List<Long>>

    fun observeIsFavorite(id: Long): Flow<Boolean>

    suspend fun getFavorite(id: Long): FavoriteWallpaper?

    suspend fun upsert(favorite: FavoriteWallpaper)

    suspend fun delete(id: Long)
}
