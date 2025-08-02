package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.toPhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

class LoadMoreSunsetPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(
        page: Int,
        perPage: Int,
        totalResults: Int
    ): PhotoExpanded? {
        val query = listOf("sunset")
        val hasMore = page + 1 < totalResults / perPage
        return if(hasMore) {
            photoRepository.getPhotos(
                query = query,
                page = page + 1,
                perPage = 30
            ).toPhotoExpanded()
        } else null
    }
}