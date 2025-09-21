package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.testdoubles.FakePhotoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetSunsetPhotosUseCaseTest {
    private val fakePhotoRepository = FakePhotoRepository()
    private val getSunsetPhotosUseCase = GetSunsetPhotosUseCase(fakePhotoRepository)

    @Test
    fun `when GetSunsetPhotosUseCase is invoked then calls repo with 'sunset', page=1, perPage=30`() =
        runTest {
            // When
            getSunsetPhotosUseCase()

            // Then
            val last = requireNotNull(fakePhotoRepository.lastCallOrNull)
            assertEquals(listOf("sunset"), last.query)
            assertEquals(1, last.page)
            assertEquals(30, last.perPage)
        }
}