package com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoListRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoListRepositoryTest {
    private val pexelsDataSource = FakePexelsDataSource()
    private val photoListRepository = PhotoListRepositoryImpl(pexelsDataSource)

    @Test
    fun `Items should be mapped to Photo`() = runTest {
        // Given
        val expectedPhoto = photoTestData()

        // When
        val result = photoListRepository.getPhotos(
            query = listOf("sunset"),
            page = 1,
            perPage = 30
        )
        val resultPhoto = result.photos.first()

        // Then
        assertEquals(expectedPhoto, resultPhoto)
    }
}