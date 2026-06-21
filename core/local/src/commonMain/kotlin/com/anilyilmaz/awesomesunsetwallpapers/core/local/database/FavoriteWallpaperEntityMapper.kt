package com.anilyilmaz.awesomesunsetwallpapers.core.local.database

import com.anilyilmaz.awesomesunsetwallpapers.core.local.model.FavoriteWallpaper

internal fun FavoriteWallpaperEntity.toFavoriteWallpaper(): FavoriteWallpaper =
    FavoriteWallpaper(
        id = id,
        photographer = photographer,
        photographerUrl = photographerUrl,
        originalUrl = originalUrl,
        large2xUrl = large2xUrl,
        largeUrl = largeUrl,
        mediumUrl = mediumUrl,
        smallUrl = smallUrl,
        portraitUrl = portraitUrl,
        landscapeUrl = landscapeUrl,
        tinyUrl = tinyUrl,
        favoritedAt = favoritedAt,
    )

internal fun FavoriteWallpaper.toEntity(): FavoriteWallpaperEntity =
    FavoriteWallpaperEntity(
        id = id,
        photographer = photographer,
        photographerUrl = photographerUrl,
        originalUrl = originalUrl,
        large2xUrl = large2xUrl,
        largeUrl = largeUrl,
        mediumUrl = mediumUrl,
        smallUrl = smallUrl,
        portraitUrl = portraitUrl,
        landscapeUrl = landscapeUrl,
        tinyUrl = tinyUrl,
        favoritedAt = favoritedAt,
    )
