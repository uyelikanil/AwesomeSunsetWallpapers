package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotoRepositoryImpl(
    private val pexelsDataSource: PexelsDataSource,
    private val ioDispatcher: CoroutineDispatcher
): PhotoRepository {
    override suspend fun getPhoto(id: Long): PexelsPhoto = withContext(ioDispatcher) {
        pexelsDataSource.getPhoto(id)}

    override suspend fun getPhotos(
        query: List<String>,
        page: Int,
        perPage: Int,
    ): PexelsPhotoExpanded {
        return pexelsDataSource.getPhotos(query, page, perPage)
    }
}