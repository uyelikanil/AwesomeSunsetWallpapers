package com.anilyilmaz.awesomesunsetwallpapers.core.local.datasource

import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.FavoriteWallpaperDao
import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.toEntity
import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.toFavoriteWallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.local.model.FavoriteWallpaper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomFavoriteWallpaperDataSource(
    private val favoriteWallpaperDao: FavoriteWallpaperDao,
) : FavoriteWallpaperLocalDataSource {
    override fun observeFavorites(): Flow<List<FavoriteWallpaper>> =
        favoriteWallpaperDao.observeFavorites()
            .map { favorites -> favorites.map { it.toFavoriteWallpaper() } }

    override fun observeFavoriteIds(): Flow<List<Long>> =
        favoriteWallpaperDao.observeFavoriteIds()

    override fun observeIsFavorite(id: Long): Flow<Boolean> =
        favoriteWallpaperDao.observeIsFavorite(id)

    override suspend fun getFavorite(id: Long): FavoriteWallpaper? =
        favoriteWallpaperDao.getFavorite(id)?.toFavoriteWallpaper()

    override suspend fun upsert(favorite: FavoriteWallpaper) {
        favoriteWallpaperDao.upsert(favorite.toEntity())
    }

    override suspend fun delete(id: Long) {
        favoriteWallpaperDao.delete(id)
    }
}
