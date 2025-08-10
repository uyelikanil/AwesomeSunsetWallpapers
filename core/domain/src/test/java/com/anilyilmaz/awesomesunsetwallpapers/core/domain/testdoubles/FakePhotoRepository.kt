package com.anilyilmaz.awesomesunsetwallpapers.core.domain.testdoubles

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoListRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoExpandedTestData

internal class FakePhotoRepository : PhotoListRepository {
    data class Call(val query: List<String>, val page: Int, val perPage: Int)

    private val _calls = mutableListOf<Call>()
    val calls: List<Call> get() = _calls
    val lastCallOrNull: Call? get() = _calls.lastOrNull()

    override suspend fun getPhotos(query: List<String>, page: Int, perPage: Int): PhotoExpanded {
        _calls += Call(query, page, perPage)
        return photoExpandedTestData()
    }
}