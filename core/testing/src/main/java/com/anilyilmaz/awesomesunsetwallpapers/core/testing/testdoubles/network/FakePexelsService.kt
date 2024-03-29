package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network

import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoTestData

class FakePexelsService: PexelsService {

    override suspend fun getPhoto(id: Int): PexelsPhoto =
        pexelsPhotoTestData(id)

    override suspend fun getPhotosWithQuery(
        query: List<String>?,
        page: Int?,
        per_page: Int?
    ): PexelsPhotoExpanded =
        pexelsPhotoExpandedTestData()
}