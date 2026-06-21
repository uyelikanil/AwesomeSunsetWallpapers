package com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.local.model.FavoriteWallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

fun Photo.toFavoriteWallpaper(favoritedAt: Long): FavoriteWallpaper =
    FavoriteWallpaper(
        id = id,
        photographer = photographer,
        photographerUrl = photographer_url,
        originalUrl = src.original,
        large2xUrl = src.large2x,
        largeUrl = src.large,
        mediumUrl = src.medium,
        smallUrl = src.small,
        portraitUrl = src.portrait,
        landscapeUrl = src.landscape,
        tinyUrl = src.tiny,
        favoritedAt = favoritedAt,
    )

fun FavoriteWallpaper.toPhoto(): Photo =
    Photo(
        id = id,
        photographer = photographer,
        photographer_url = photographerUrl,
        src = PexelsPhotoSrc(
            original = originalUrl,
            large2x = large2xUrl,
            large = largeUrl,
            medium = mediumUrl,
            small = smallUrl,
            portrait = portraitUrl,
            landscape = landscapeUrl,
            tiny = tinyUrl,
        ),
        isFavorite = true,
    )
