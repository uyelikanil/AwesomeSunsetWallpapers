package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded

interface PhotoRepository {
    suspend fun getPhoto(id: Long): PexelsPhoto

    suspend fun getPhotos(query: List<String>, page: Int, perPage: Int): PexelsPhotoExpanded
}