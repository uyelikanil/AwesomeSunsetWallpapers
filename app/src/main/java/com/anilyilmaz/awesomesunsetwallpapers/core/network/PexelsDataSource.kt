package com.anilyilmaz.awesomesunsetwallpapers.core.network

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.NetworkResponse
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

interface PexelsDataSource {
    suspend fun getPhoto(id: Int): NetworkResponse<PexelsPhoto>

    suspend fun getPhotosWithQuery(query: List<String>?, page: Int?, per_page: Int?) :
            NetworkResponse<PexelsPhotoExpanded>
}