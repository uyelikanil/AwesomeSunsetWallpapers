package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoListRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

class GetSunsetPhotosUseCase(
    private val photoListRepository: PhotoListRepository
) {
    suspend operator fun invoke(page: Int = 1): PhotoExpanded {
        val query = listOf("sunset")
        return photoListRepository.getPhotos(query, page, 30)
    }
}