package com.anilyilmaz.awesomesunsetwallpapers.core.model

import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

data class Photo(
    val id: Int,
    val photographer: String = "",
    val photographer_url: String = "",
    val src: PexelsPhotoSrc
)