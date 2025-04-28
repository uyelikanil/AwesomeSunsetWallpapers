package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

fun photoTestData(id: Long = 0) = Photo(
    id = id,
    photographer = "Photographer",
    photographer_url = "Photographer URL",
    src = PexelsPhotoSrc(
        original = "original URL",
        large2x = "large2x URL",
        large = "large URL",
        medium = "medium URL",
        small = "small URL",
        portrait = "portrait URL",
        landscape = "landscape URL",
        tiny = "tiny URL"
    )
)