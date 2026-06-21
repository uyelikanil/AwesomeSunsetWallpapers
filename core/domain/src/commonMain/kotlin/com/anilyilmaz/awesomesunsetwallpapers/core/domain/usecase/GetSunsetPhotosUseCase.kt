package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoListRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.withFavoriteInfo
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import kotlinx.coroutines.flow.first

class GetSunsetPhotosUseCase(
    private val photoListRepository: PhotoListRepository,
    private val favoriteWallpaperRepository: FavoriteWallpaperRepository,
) {
    suspend operator fun invoke(page: Int = 1): PhotoExpanded {
        val query = listOf("sunset")
        val photoExpanded = photoListRepository.getPhotos(query, page, 30)
        val favoriteIds = favoriteWallpaperRepository.observeFavoriteIds().first()

        return photoExpanded.withFavoriteInfo(favoriteIds)
    }
}
