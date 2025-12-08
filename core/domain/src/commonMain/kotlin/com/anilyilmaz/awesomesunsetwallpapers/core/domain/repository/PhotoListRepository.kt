package com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository

import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded

interface PhotoListRepository {
    suspend fun getPhotos(query: List<String>, page: Int, perPage: Int): PhotoExpanded
}