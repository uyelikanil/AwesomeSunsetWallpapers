package com.anilyilmaz.awesomesunsetwallpapers.core.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_wallpapers")
internal data class FavoriteWallpaperEntity(
    @PrimaryKey val id: Long,
    val photographer: String,
    val photographerUrl: String,
    val originalUrl: String,
    val large2xUrl: String,
    val largeUrl: String,
    val mediumUrl: String,
    val smallUrl: String,
    val portraitUrl: String,
    val landscapeUrl: String,
    val tinyUrl: String,
    val favoritedAt: Long,
)
