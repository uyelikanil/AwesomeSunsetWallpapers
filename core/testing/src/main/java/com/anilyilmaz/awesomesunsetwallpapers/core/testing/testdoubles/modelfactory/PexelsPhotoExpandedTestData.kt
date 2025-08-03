package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

fun pexelsPhotoExpandedTestData(page: Int = 1, perPage: Int = 30, totalResults: Int = 8000) =
    PexelsPhotoExpanded(
        total_results = totalResults,
        page = page,
        per_page = perPage,
        photos = listOf(pexelsPhotoTestData()),
        next_page = "next page URL"
    )