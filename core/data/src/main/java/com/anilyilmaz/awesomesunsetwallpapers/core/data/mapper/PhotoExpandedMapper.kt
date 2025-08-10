package com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

fun PexelsPhotoExpanded.toPhotoExpanded(): PhotoExpanded = PhotoExpanded(
    totalResults = total_results,
    page = page,
    perPage = per_page,
    photos = photos.map { it.toPhoto() }
)