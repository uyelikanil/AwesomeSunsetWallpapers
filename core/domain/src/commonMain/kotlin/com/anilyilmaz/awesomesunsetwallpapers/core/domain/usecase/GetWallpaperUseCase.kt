package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo

class GetWallpaperUseCase(
    private val photoRepository: PhotoRepository,
    private val favoriteWallpaperRepository: FavoriteWallpaperRepository,
) {
    suspend operator fun invoke(id: Long): Photo {
        favoriteWallpaperRepository.getFavorite(id)?.let { favorite ->
            return favorite.copy(isFavorite = true)
        }

        return photoRepository.getPhoto(id).copy(isFavorite = false)
    }
}
