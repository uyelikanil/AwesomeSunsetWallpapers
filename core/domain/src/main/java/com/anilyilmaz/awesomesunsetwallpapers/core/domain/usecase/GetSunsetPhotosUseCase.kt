package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSunsetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val photoMapper: PhotoMapper
) {
    operator fun invoke(): Flow<PagingData<Photo>> {
        val query = listOf("sunset")
        val pexelsPhotosPagingData = photoRepository.getPhotosWithQuery(query)

        return pexelsPhotosPagingData.map { pagingData ->
            pagingData.map { photoMapper.mapToPhoto(it) }
        }
    }
}

