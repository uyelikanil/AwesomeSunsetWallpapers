package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoadMoreSunsetPhotosUseCaseTest {
    private lateinit var usecase: LoadMoreSunsetPhotosUseCase
    private val pexelsDataSource = FakePexelsDataSource()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()
    private val photoRepository = PhotoRepositoryImpl(pexelsDataSource, dispatcher)

    @Before
    fun setUp() {
        usecase = LoadMoreSunsetPhotosUseCase(photoRepository)
    }

    @Test
    fun `given currentPage, perPage and totalResults, when LoadMoreSunsetPhotos is called, then should get next page as PhotoExpanded`() =
        runTest {
            // Given
            val currentPage = 1
            val perPage = 30
            val totalResults = 8000
            val expectedPhotoExpanded = photoExpandedTestData(
                page = currentPage + 1,
                perPage = perPage,
                totalResults = totalResults
            )

            // When
            val result = usecase(
                page = currentPage,
                perPage = perPage,
                totalResults = totalResults
            )

            // Then
            assertEquals(expectedPhotoExpanded, result)
        }
}