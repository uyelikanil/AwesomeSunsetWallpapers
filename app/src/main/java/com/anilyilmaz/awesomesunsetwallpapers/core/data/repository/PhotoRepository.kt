package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

interface PhotoRepository {
    suspend fun getPhoto(id: Int): PexelsPhoto

    suspend fun getPhotosWithQuery(query: List<String>?, page: Int?, per_page: Int?) :
            PexelsPhotoExpanded
}