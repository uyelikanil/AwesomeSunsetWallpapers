package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhoto(id: Long): PexelsPhoto

    fun getPhotosWithQuery(query: List<String>): Flow<PagingData<PexelsPhoto>>
}