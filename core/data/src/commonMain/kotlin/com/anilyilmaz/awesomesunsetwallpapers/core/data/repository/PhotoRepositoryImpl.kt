package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper.toPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotoRepositoryImpl(
    private val pexelsDataSource: PexelsDataSource,
    private val ioDispatcher: CoroutineDispatcher
): PhotoRepository {
    override suspend fun getPhoto(id: Long): Photo = withContext(ioDispatcher) {
        pexelsDataSource.getPhoto(id).toPhoto()
    }
}
