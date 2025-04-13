package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.toPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(id: Int): Photo = photoRepository.getPhoto(id).toPhoto()
}

