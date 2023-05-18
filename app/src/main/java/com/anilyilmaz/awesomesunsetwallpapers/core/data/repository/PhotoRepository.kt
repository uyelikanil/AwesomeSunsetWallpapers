package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhoto(id: Int): PexelsPhoto

    suspend fun getPhotosWithQuery(query: List<String>, per_page: Int) :
            Flow<PagingData<PexelsPhoto>>
}