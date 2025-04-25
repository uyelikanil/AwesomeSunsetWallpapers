package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.toPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo

class GetPhotoUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(id: Long): Photo = photoRepository.getPhoto(id).toPhoto()
}