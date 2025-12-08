package com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo

interface PhotoRepository {
    suspend fun getPhoto(id: Long): Photo
}