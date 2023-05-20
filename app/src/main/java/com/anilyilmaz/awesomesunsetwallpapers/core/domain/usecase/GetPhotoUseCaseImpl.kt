package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPhotoUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepository,
    private val photoMapper: PhotoMapper): GetPhotoUseCase {

    override suspend fun getPhoto(id: Int): Photo =
        photoMapper.mapToPhoto(photoRepository.getPhoto(id))

    override fun getPhotos(query: List<String>, per_page: Int): Flow<PagingData<Photo>> =
        photoRepository.getPhotosWithQuery(query, per_page).map {
            photoMapper.mapPexelsPhotosToPhoto(it)}
}