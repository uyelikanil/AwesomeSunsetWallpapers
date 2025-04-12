package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PexelsDataSourceImpl @Inject constructor(
    okhttpCallFactory: Call.Factory, networkJson: Json
): PexelsDataSource {
    private val pexelsApi = Retrofit.Builder()
        .baseUrl("https://api.pexels.com")
        .callFactory(okhttpCallFactory)
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
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
                enablePlaceholders = true),
            pagingSourceFactory = { PexelsPagingSource(pexelsApi, query, per_page * 3) }
        ).flow
    }
}