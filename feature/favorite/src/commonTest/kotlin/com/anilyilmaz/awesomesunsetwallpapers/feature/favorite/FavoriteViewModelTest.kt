package com.anilyilmaz.awesomesunsetwallpapers.feature.favorite

import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.data.FakeFavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util.MainDispatcherBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest : MainDispatcherBase() {
    private lateinit var repository: FakeFavoriteWallpaperRepository
    private lateinit var viewModel: FavoriteViewModel

    @BeforeTest
    fun setUp() {
        installMain()
        repository = FakeFavoriteWallpaperRepository()
        viewModel = FavoriteViewModel(repository)
    }

    @AfterTest
    fun tearDown() {
        resetMain()
    }

    @Test
    fun `toggle favorite adds and removes wallpaper`() = runTest {
        val photo = photoTestData(42)

        viewModel.toggleFavorite(photo)
        advanceUntilIdle()

        assertEquals(listOf(photo.copy(isFavorite = true)), repository.observeFavorites().first())

        viewModel.toggleFavorite(photo)
        advanceUntilIdle()

        assertEquals(emptyList(), repository.observeFavorites().first())
    }
}
