package com.anilyilmaz.awesomesunsetwallpapers.core.network.api

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsService {
    @GET(value = "/v1/photos/{id}")
    suspend fun getPhoto(@Path("id") id: Int): PexelsPhoto

    @GET(value = "/v1/search")
    suspend fun getPhotosWithQuery(
        @Query("query") query: List<String>?,
        @Query("page") page: Int?, @Query("per_page") per_page: Int?):
            PexelsPhotoExpanded
}