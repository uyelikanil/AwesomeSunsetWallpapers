package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsPagingSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.flow.Flow

class FakePexelsDataSource: PexelsDataSource {
    private val mockClient = MockNetworkModule.createHttpClient()
    private val api = PexelsService(mockClient)

    override suspend fun getPhoto(id: Long): PexelsPhoto = api.getPhoto(id)

    override fun getPhotosWithQuery(
        query: List<String>
    ): Flow<PagingData<PexelsPhoto>> {
        return Pager(config = PagingConfig(
            pageSize = PER_PAGE,
            enablePlaceholders = true),
            pagingSourceFactory = { PexelsPagingSource(api, query, PER_PAGE * 3) }
        ).flow
    }

    companion object {
        private const val PER_PAGE = 1
    }
}