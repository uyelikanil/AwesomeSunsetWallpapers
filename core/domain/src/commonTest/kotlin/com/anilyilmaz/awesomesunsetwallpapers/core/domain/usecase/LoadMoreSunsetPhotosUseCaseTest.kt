package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.testdoubles.FakePhotoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadMoreSunsetPhotosUseCaseTest {
    private val fakePhotoRepository = FakePhotoRepository()
    private val getSunsetPhotosUseCase = GetSunsetPhotosUseCase(fakePhotoRepository)
    private val loadMoreSunsetPhotosUseCase = LoadMoreSunsetPhotosUseCase(getSunsetPhotosUseCase)

    @Test
    fun `given page, perPage and totalResults, when LoadMoreSunsetPhotosUseCase is invoked then loads next page`() =
        runTest {
            // Given
            val page = 1
            val perPage = 30
            val totalResults = 8000

            // When
            loadMoreSunsetPhotosUseCase(
                page = page,
                perPage = perPage,
                totalResults = totalResults
            )

            // Then
            val last = requireNotNull(fakePhotoRepository.lastCallOrNull)
            assertEquals(listOf("sunset"), last.query)
            assertEquals(2, last.page)
            assertEquals(30, last.perPage)
        }
}