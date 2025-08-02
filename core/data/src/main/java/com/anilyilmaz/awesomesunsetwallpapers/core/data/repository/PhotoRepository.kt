package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhoto(id: Long): PexelsPhoto

    suspend fun getPhotos(query: List<String>, page: Int, perPage: Int): PexelsPhotoExpanded

    fun getPhotosWithQuery(query: List<String>): Flow<PagingData<PexelsPhoto>>
}