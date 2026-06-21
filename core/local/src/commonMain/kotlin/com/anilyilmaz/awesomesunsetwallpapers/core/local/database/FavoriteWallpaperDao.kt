package com.anilyilmaz.awesomesunsetwallpapers.core.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavoriteWallpaperDao {
    @Query("SELECT * FROM favorite_wallpapers ORDER BY favoritedAt DESC")
    fun observeFavorites(): Flow<List<FavoriteWallpaperEntity>>

    @Query("SELECT id FROM favorite_wallpapers")
    fun observeFavoriteIds(): Flow<List<Long>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_wallpapers WHERE id = :id)")
    fun observeIsFavorite(id: Long): Flow<Boolean>

    @Query("SELECT * FROM favorite_wallpapers WHERE id = :id LIMIT 1")
    suspend fun getFavorite(id: Long): FavoriteWallpaperEntity?

    @Upsert
    suspend fun upsert(favorite: FavoriteWallpaperEntity)

    @Query("DELETE FROM favorite_wallpapers WHERE id = :id")
    suspend fun delete(id: Long)
}
