package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    getSunsetPhotosUseCase: GetSunsetPhotosUseCase
): ViewModel() {
    val getWallpapers: Flow<PagingData<Photo>> = getSunsetPhotosUseCase().cachedIn(viewModelScope)
}