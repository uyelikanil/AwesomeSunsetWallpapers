package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

fun pexelsPhotoTestData(id: Long = 0) = PexelsPhoto(
    id = id,
    width = 4096,
    height = 2730,
    url = "Url",
    photographer = "Photographer",
    photographer_url = "Photographer URL",
    photographer_id = 0, avg_color = "#000000",
    src = PexelsPhotoSrc(
        original = "original URL",
        large2x = "large2x URL",
        large = "large URL",
        medium = "medium URL",
        small = "small URL",
        portrait = "portrait URL",
        landscape = "landscape URL",
        tiny = "tiny URL"
    ),
    liked = true,
    alt = "alt name"
)