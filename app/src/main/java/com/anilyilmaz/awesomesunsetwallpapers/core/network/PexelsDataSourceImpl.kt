package com.anilyilmaz.awesomesunsetwallpapers.core.network

import com.anilyilmaz.awesomesunsetwallpapers.BuildConfig
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.NetworkResponse
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import okhttp3.Call
import retrofit2.Retrofit
import javax.inject.Inject

class PexelsDataSourceImpl @Inject constructor(okhttpCallFactory: Call.Factory): PexelsDataSource {
    private val pexelsApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(okhttpCallFactory)
        .build()
        .create(PexelsService::class.java)

    override suspend fun getPhoto(id: Int): NetworkResponse<PexelsPhoto> =
        pexelsApi.getPhoto(id)

    override suspend fun getPhotosWithQuery(
        query: List<String>?,
        page: Int?,
        per_page: Int?): NetworkResponse<PexelsPhotoExpanded> =
        pexelsApi.getPhotosWithQuery(query, page, per_page)
}