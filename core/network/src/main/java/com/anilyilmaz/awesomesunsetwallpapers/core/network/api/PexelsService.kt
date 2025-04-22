package com.anilyilmaz.awesomesunsetwallpapers.core.network.api

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PexelsService(private val client: HttpClient) {
    suspend fun getPhoto(id: Long): PexelsPhoto =
        client.get("/v1/photos/$id").body()

    suspend fun getPhotosWithQuery(
        query: List<String>?,
        page: Int?,
        per_page: Int?
    ): PexelsPhotoExpanded = client.get("/v1/search") {
        parameter("query", query?.joinToString(","))
        parameter("page", page)
        parameter("per_page", per_page)
    }.body()
}