package com.anilyilmaz.awesomesunsetwallpapers.core.data

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.network.FakePexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FakePhotoRepositoryImpl(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):
    PhotoRepository {

    private val dataSource = FakePexelsDataSource()

    override suspend fun getPhoto(id: Int): PexelsPhoto = withContext(ioDispatcher) {
        dataSource.getPhoto(id)
    }

    override fun getPhotosWithQuery(
        query: List<String>,
        per_page: Int
    ): Flow<PagingData<PexelsPhoto>> = dataSource.getPhotosWithQuery(query, per_page)
}