package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper.toPhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoListRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource

class PhotoListRepositoryImpl(
    private val pexelsDataSource: PexelsDataSource,
): PhotoListRepository {
    override suspend fun getPhotos(
        query: List<String>,
        page: Int,
        perPage: Int,
    ): PhotoExpanded {
        return pexelsDataSource.getPhotos(query, page, perPage).toPhotoExpanded()
    }
}