package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.common.AswDispatchers.IO
import com.anilyilmaz.awesomesunsetwallpapers.core.common.Dispatcher
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val pexelsDataSource: PexelsDataSource,
                                              @Dispatcher(IO) private val ioDispatcher:
                                              CoroutineDispatcher): PhotoRepository {

    override suspend fun getPhoto(id: Int): PexelsPhoto = withContext(ioDispatcher) {
        pexelsDataSource.getPhoto(id)}

    override fun getPhotosWithQuery(query: List<String>, per_page: Int):
            Flow<PagingData<PexelsPhoto>> = pexelsDataSource.getPhotosWithQuery(query, per_page)
}