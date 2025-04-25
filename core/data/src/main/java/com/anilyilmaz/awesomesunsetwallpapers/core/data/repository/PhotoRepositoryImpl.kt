package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PhotoRepositoryImpl(
    private val pexelsDataSource: PexelsDataSource,
    private val ioDispatcher: CoroutineDispatcher
): PhotoRepository {
    override suspend fun getPhoto(id: Long): PexelsPhoto = withContext(ioDispatcher) {
        pexelsDataSource.getPhoto(id)}

    override fun getPhotosWithQuery(query: List<String>):
            Flow<PagingData<PexelsPhoto>> = pexelsDataSource.getPhotosWithQuery(query)
}