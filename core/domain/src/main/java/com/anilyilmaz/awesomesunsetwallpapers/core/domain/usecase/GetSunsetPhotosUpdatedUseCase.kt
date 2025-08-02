package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.toPhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

class GetSunsetPhotosUpdatedUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(): PhotoExpanded {
        val query = listOf("sunset")
        return photoRepository.getPhotos(query, 1, 30).toPhotoExpanded()
    }
}