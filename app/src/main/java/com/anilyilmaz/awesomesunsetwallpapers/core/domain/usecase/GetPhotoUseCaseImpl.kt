package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import javax.inject.Inject

class GetPhotoUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepository,
    private val photoMapper: PhotoMapper): GetPhotoUseCase {

    override suspend fun getPhoto(id: Int): Photo =
        photoMapper.mapToPhoto(photoRepository.getPhoto(id))

    override suspend fun getPhotos(query: List<String>?, page: Int?, per_page: Int?): List<Photo> =
        photoMapper.mapPexelsPhotosToPhoto(photoRepository.getPhotosWithQuery(query, page, per_page))
}