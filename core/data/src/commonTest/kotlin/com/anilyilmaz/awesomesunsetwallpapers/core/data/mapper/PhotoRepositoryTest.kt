package com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PhotoRepositoryTest {
    private val pexelsDataSource = FakePexelsDataSource()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()
    private val photoRepository = PhotoRepositoryImpl(pexelsDataSource, dispatcher)

    @Test
    fun `given photo id, when getPhoto is called, then get seleceted PexelsPhoto with id as Photo`()
            = runTest {
        // Given
        val expectedPhoto = photoTestData(0)

        // When
        val result = photoRepository.getPhoto(0)

        // Then
        assertEquals(expectedPhoto, result)
    }
}