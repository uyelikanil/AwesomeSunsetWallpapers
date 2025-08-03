package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

fun photoExpandedTestData(page: Int = 1, perPage: Int = 30, totalResults: Int = 8000) =
    PhotoExpanded(
        totalResults = totalResults,
        page = page,
        perPage = perPage,
        photos = listOf(photoTestData())
    )