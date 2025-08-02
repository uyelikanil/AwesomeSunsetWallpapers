package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.NetworkModule
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import kotlinx.coroutines.flow.Flow

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

    override fun getPhotosWithQuery(
        query: List<String>
    ): Flow<PagingData<PexelsPhoto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PER_PAGE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                PexelsPagingSource(pexelsService, query, PER_PAGE * 3)
            }
        ).flow
    }

    companion object {
        private const val PER_PAGE = 30
    }
}