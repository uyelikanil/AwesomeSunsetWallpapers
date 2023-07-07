package com.anilyilmaz.awesomesunsetwallpapers.core.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

fun pexelsPhotoTestData(id: Int = 0) = PexelsPhoto(
    id,
    4096,
    2730,
    "Url",
    "Photographer", "Photographer URL",
    0, "#000000",
    PexelsPhotoSrc("original URL",
        "large2x URL",
        "large URL",
        "medium URL",
        "small URL",
        "portrait URL",
        "landscape URL",
        "tiny URL"),
    true,
    "alt name")