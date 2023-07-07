package com.anilyilmaz.awesomesunsetwallpapers.testdoubles.network

import com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory.pexelsPhotoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory.pexelsPhotoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

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