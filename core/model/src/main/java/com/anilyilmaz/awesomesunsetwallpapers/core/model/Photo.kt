package com.anilyilmaz.awesomesunsetwallpapers.core.model

import androidx.compose.runtime.Immutable
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

@Immutable
data class Photo(
    val id: Int,
    val photographer: String = "",
    val photographer_url: String = "",
    val src: PexelsPhotoSrc
)