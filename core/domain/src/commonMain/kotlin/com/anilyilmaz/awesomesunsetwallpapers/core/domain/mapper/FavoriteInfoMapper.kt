package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

internal fun PhotoExpanded.withFavoriteInfo(favoriteIds: Set<Long>): PhotoExpanded =
    copy(
        photos = photos.map { photo -> photo.withFavoriteInfo(favoriteIds) }
    )

internal fun Photo.withFavoriteInfo(favoriteIds: Set<Long>): Photo =
    copy(isFavorite = id in favoriteIds)
