package com.anilyilmaz.awesomesunsetwallpapers.feature.home

data class LoadState(
    val refresh: LoadStatus,
    val append: LoadStatus,
)