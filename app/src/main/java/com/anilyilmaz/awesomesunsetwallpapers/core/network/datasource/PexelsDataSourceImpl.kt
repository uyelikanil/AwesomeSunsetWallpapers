package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.BuildConfig
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.NetworkResponse
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.flow.Flow
import okhttp3.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PexelsDataSourceImpl @Inject constructor(okhttpCallFactory: Call.Factory): PexelsDataSource {
    private val pexelsApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(okhttpCallFactory)
        .build()
        .create(PexelsService::class.java)

    override suspend fun getPhoto(id: Int): NetworkResponse<PexelsPhoto> =
        pexelsApi.getPhoto(id)

    override fun getPhotosWithQuery(
        query: List<String>,
        per_page: Int
    ): Flow<PagingData<PexelsPhoto>> {
        return Pager(
            config = PagingConfig(pageSize = per_page),
            pagingSourceFactory = { PexelsPagingSource(pexelsApi, query, per_page) }
        ).flow
    }
}