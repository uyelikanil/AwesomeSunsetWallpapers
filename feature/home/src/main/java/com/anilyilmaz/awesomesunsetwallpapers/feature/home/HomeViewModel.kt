package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
): ViewModel() {
    var getWallpapers: Flow<PagingData<Photo>> =
        getPhotoUseCase.getPhotos(listOf("sunset"), 30).cachedIn(viewModelScope)
}