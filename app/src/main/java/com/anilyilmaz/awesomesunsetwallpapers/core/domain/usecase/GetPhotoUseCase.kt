package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo

interface GetPhotoUseCase {
    suspend fun getPhoto(id: Int): Photo

    suspend fun getPhotos(query: List<String>?, page: Int?, per_page: Int?): List<Photo>
}