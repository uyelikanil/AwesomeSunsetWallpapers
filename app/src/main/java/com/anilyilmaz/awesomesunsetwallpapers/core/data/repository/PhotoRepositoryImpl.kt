package com.anilyilmaz.awesomesunsetwallpapers.core.data.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.common.AswDispatchers.IO
import com.anilyilmaz.awesomesunsetwallpapers.core.common.Dispatcher
import com.anilyilmaz.awesomesunsetwallpapers.core.network.PexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val pexelsDataSource: PexelsDataSource,
@Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher): PhotoRepository {

    override suspend fun getPhoto(id: Int): PexelsPhoto = withContext(ioDispatcher) {
        pexelsDataSource.getPhoto(id).data}

    override suspend fun getPhotosWithQuery(query: List<String>?, page: Int?, per_page: Int?):
            PexelsPhotoExpanded = withContext(ioDispatcher){
        pexelsDataSource.getPhotosWithQuery(query, page, per_page).data}
}