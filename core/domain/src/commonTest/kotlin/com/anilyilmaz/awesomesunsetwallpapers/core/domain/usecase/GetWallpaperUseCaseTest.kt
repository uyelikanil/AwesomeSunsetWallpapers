package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.data.FakeFavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetWallpaperUseCaseTest {
    @Test
    fun `when wallpaper is favorite then returns local favorite wallpaper`() =
        runTest {
            val favoritePhoto = photoTestData(id = 99)
            val useCase = GetWallpaperUseCase(
                photoRepository = FakePhotoRepository(photoTestData(id = 1)),
                favoriteWallpaperRepository = FakeFavoriteWallpaperRepository(
                    initialFavorites = listOf(favoritePhoto)
                ),
            )

            val result = useCase(99)

            assertEquals(favoritePhoto.copy(isFavorite = true), result)
        }

    @Test
    fun `when wallpaper is not favorite then returns network wallpaper as not favorite`() =
        runTest {
            val photo = photoTestData(id = 1)
            val useCase = GetWallpaperUseCase(
                photoRepository = FakePhotoRepository(photo),
                favoriteWallpaperRepository = FakeFavoriteWallpaperRepository(),
            )

            val result = useCase(1)

            assertEquals(photo, result)
            assertTrue(!result.isFavorite)
        }

    private class FakePhotoRepository(
        private val photo: Photo,
    ) : PhotoRepository {
        override suspend fun getPhoto(id: Long): Photo = photo
    }
}
