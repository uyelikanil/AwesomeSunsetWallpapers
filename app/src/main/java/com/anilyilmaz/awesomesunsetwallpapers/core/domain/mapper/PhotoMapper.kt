package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhoto
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoExpanded
import javax.inject.Inject

class PhotoMapper @Inject constructor() {
    fun mapToPhoto(pexelsPhoto: PexelsPhoto): Photo = Photo(
        pexelsPhoto.id,
        pexelsPhoto.photographer,
        pexelsPhoto.photographer_url,
        pexelsPhoto.src
    )

    fun mapPexelPhotosToPhoto(pexelsPhotoExpanded: PexelsPhotoExpanded): List<Photo> =
        pexelsPhotoExpanded.photos.map{mapToPhoto(it)
    }
}