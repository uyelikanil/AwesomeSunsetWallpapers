package com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anilyilmaz.awesomesunsetwallpapers.core.network.api.PexelsService
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto

class PexelsPagingSource (
    private val pexelsService: PexelsService,
    private val query: List<String>,
    private val perPage: Int
): PagingSource<Int, PexelsPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PexelsPhoto> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = pexelsService.getPhotosWithQuery(query, nextPageNumber, perPage)

            val nextKey = if (nextPageNumber < (response.total_results / perPage))
                nextPageNumber + 1 else null

            LoadResult.Page(
                data = response.photos,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PexelsPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}