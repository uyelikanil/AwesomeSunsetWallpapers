package com.anilyilmaz.awesomesunsetwallpapers.feature.home

sealed interface LoadStatus {
    data object Loading : LoadStatus
    data object NotLoading : LoadStatus
    data object Error : LoadStatus
}