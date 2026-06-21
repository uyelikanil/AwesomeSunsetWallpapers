package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.testdoubles.FakePhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.data.FakeFavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetSunsetPhotosUseCaseTest {
    private val fakePhotoRepository = FakePhotoRepository()
    private val favoriteWallpaperRepository = FakeFavoriteWallpaperRepository(
        initialFavorites = listOf(photoTestData())
    )
    private val getSunsetPhotosUseCase = GetSunsetPhotosUseCase(
        fakePhotoRepository,
        favoriteWallpaperRepository,
    )

    @Test
    fun `when GetSunsetPhotosUseCase is invoked then calls repo with sunset page 1 perPage 30`() =
        runTest {
            // When
            getSunsetPhotosUseCase()

            // Then
            val last = requireNotNull(fakePhotoRepository.lastCallOrNull)
            assertEquals(listOf("sunset"), last.query)
            assertEquals(1, last.page)
            assertEquals(30, last.perPage)
        }

    @Test
    fun `when GetSunsetPhotosUseCase is invoked then marks favorite wallpapers`() =
        runTest {
            val result = getSunsetPhotosUseCase()

            assertEquals(true, result.photos.first().isFavorite)
        }
}
