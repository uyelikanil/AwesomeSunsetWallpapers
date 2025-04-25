package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.toPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSunsetPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    operator fun invoke(): Flow<PagingData<Photo>> {
        val query = listOf("sunset")
        val pexelsPhotosPagingData = photoRepository.getPhotosWithQuery(query)

        return pexelsPhotosPagingData.map { pagingData ->
            pagingData.map { it.toPhoto() }
        }
    }
}