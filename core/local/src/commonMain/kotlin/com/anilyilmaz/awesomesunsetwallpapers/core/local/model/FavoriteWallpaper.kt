package com.anilyilmaz.awesomesunsetwallpapers.core.local.model

data class FavoriteWallpaper(
    val id: Long,
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
