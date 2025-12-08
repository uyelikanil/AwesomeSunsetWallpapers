package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

class LoadMoreSunsetPhotosUseCase(
    private val getSunsetPhotosUseCase: GetSunsetPhotosUseCase
) {
    suspend operator fun invoke(
        page: Int,
        perPage: Int,
        totalResults: Int
    ): PhotoExpanded? {
        val hasMore = page + 1 < totalResults / perPage
        return if(hasMore) {
            getSunsetPhotosUseCase(
                page = page + 1
            )
        } else null
    }
}