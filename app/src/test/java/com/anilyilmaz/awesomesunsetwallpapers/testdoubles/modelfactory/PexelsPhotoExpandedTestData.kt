package com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

fun pexelsPhotoExpandedTestData() = PexelsPhotoExpanded(
    8000,
    1,
    80,
    listOf(pexelsPhotoTestData()),
    "next page URL"
)