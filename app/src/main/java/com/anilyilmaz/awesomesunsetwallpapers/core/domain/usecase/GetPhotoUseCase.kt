package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow

interface GetPhotoUseCase {
    suspend fun getPhoto(id: Int): Photo

    fun getPhotos(query: List<String>, per_page: Int): Flow<PagingData<Photo>>
}