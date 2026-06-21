package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper.toFavoriteWallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper.toPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.local.datasource.FavoriteWallpaperLocalDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteWallpaperRepositoryImpl(
    private val favoriteWallpaperLocalDataSource: FavoriteWallpaperLocalDataSource,
) : FavoriteWallpaperRepository {
    override fun observeFavorites(): Flow<List<Photo>> =
        favoriteWallpaperLocalDataSource.observeFavorites()
            .map { favorites -> favorites.map { it.toPhoto() } }

    override fun observeFavoriteIds(): Flow<Set<Long>> =
        favoriteWallpaperLocalDataSource.observeFavoriteIds()
            .map { ids -> ids.toSet() }

    override fun observeIsFavorite(id: Long): Flow<Boolean> =
        favoriteWallpaperLocalDataSource.observeIsFavorite(id)

    override suspend fun getFavorite(id: Long): Photo? =
        favoriteWallpaperLocalDataSource.getFavorite(id)?.toPhoto()

    override suspend fun toggleFavorite(photo: Photo) {
        val favorite = favoriteWallpaperLocalDataSource.getFavorite(photo.id)
        if (favorite == null) {
            favoriteWallpaperLocalDataSource.upsert(
                photo.toFavoriteWallpaper(favoritedAt = currentTimeMillis())
            )
        } else {
            favoriteWallpaperLocalDataSource.delete(photo.id)
        }
    }
}

@OptIn(kotlin.time.ExperimentalTime::class)
private fun currentTimeMillis(): Long =
    kotlin.time.Clock.System.now().toEpochMilliseconds()
