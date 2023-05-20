package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.BuildConfig
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.flow.Flow
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PexelsDataSourceImpl @Inject constructor(okhttpCallFactory: Call.Factory): PexelsDataSource {
    private val pexelsApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(PexelsService::class.java)

    override suspend fun getPhoto(id: Int): PexelsPhoto =
        pexelsApi.getPhoto(id)

    override fun getPhotosWithQuery(
        query: List<String>,
        per_page: Int
    ): Flow<PagingData<PexelsPhoto>> {
        return Pager(config = PagingConfig(
                pageSize = per_page,
                initialLoadSize = per_page * 2,
                prefetchDistance = per_page * 3,
                enablePlaceholders = true),
            pagingSourceFactory = { PexelsPagingSource(pexelsApi, query, per_page*3) }
        ).flow
    }
}