package com.anilyilmaz.awesomesunsetwallpapers.core.model

data class PhotoExpanded(
    val totalResults: Int,
    val page: Int,
    val perPage: Int,
    val photos: List<Photo> = listOf()
)