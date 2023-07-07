package com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

fun photoTestData(id: Int = 0) = Photo(
    id,
    "Photographer", "Photographer URL",
    PexelsPhotoSrc("original URL",
        "large2x URL",
        "large URL",
        "medium URL",
        "small URL",
        "portrait URL",
        "landscape URL",
        "tiny URL")
)