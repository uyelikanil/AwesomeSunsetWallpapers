package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import javax.inject.Inject

class PhotoMapper @Inject constructor() {
    fun mapToPhoto(pexelsPhoto: PexelsPhoto): Photo = Photo(
        pexelsPhoto.id,
        pexelsPhoto.photographer,
        pexelsPhoto.photographer_url,
        pexelsPhoto.src
    )

    fun mapPexelsPhotosToPhoto(pexelsPhotos: PagingData<PexelsPhoto>): PagingData<Photo> =
        pexelsPhotos.map{mapToPhoto(it)}
}