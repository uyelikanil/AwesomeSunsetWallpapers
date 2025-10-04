package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

data class HomeUiState(
    val loadState: LoadState,
    val photoExpanded: PhotoExpanded? = null,
)