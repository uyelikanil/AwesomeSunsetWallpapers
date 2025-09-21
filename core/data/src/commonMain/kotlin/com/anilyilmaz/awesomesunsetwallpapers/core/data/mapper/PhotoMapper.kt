package com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto

fun PexelsPhoto.toPhoto(): Photo = Photo(
    id = id,
    photographer = photographer,
    photographer_url = photographer_url,
    src = src
)