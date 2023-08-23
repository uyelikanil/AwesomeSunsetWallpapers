package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.flow.Flow

interface PexelsDataSource {
    suspend fun getPhoto(id: Int): PexelsPhoto

    fun getPhotosWithQuery(query: List<String>, per_page: Int):
            Flow<PagingData<PexelsPhoto>>
}