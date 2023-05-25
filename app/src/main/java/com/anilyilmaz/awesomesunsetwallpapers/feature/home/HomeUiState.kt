package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.paging.PagingData
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo

data class HomeUiState (
    val pagingData: PagingData<Photo> = PagingData.empty(),
    val isLoading: Boolean = false,
    val error: Boolean = false,
)