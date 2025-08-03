package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import com.anilyilmaz.awesomesunsetwallpapers.core.network.NetworkModule
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

class PexelsDataSourceImpl: PexelsDataSource {
    private val httpClient = NetworkModule.createHttpClient("https://api.pexels.com")
    private val pexelsService = PexelsService(httpClient)

    override suspend fun getPhoto(id: Long): PexelsPhoto =
        pexelsService.getPhoto(id)

    override suspend fun getPhotos(
        query: List<String>,
        page: Int,
        perPage: Int,
    ): PexelsPhotoExpanded =
        pexelsService.getPhotosWithQuery(query, page, perPage)
}