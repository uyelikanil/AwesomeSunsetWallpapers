package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetSunsetPhotosUseCaseTest {
    private lateinit var usecase: GetSunsetPhotosUseCase
    private val pexelsDataSource = FakePexelsDataSource()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()
    private val photoRepository = PhotoRepositoryImpl(pexelsDataSource, dispatcher)

    @Before
    fun setUp() {
        usecase = GetSunsetPhotosUseCase(photoRepository)
    }

    @Test
    fun `Items should be mapped to Photo`() = runTest {
        // Given
        val expectedPhoto = photoTestData()

        // When
        val result = usecase()
        val resultPhoto = result.photos.first()

        // Then
        assertEquals(expectedPhoto, resultPhoto)
    }
}