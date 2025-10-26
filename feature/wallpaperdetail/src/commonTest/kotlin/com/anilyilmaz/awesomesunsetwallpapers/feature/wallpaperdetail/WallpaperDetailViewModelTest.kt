package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util.MainDispatcherBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WallpaperDetailViewModelTest: MainDispatcherBase() {

    private lateinit var viewModel: WallpaperDetailViewModel
    private val dataSource = FakePexelsDataSource(standardTestDispatcher)
    private val photoRepository = PhotoRepositoryImpl(dataSource, standardTestDispatcher)
    private val wallpaperId = 0L


    @BeforeTest
    fun before() { installMain() }

    @BeforeTest
    fun setUp() {
        viewModel = WallpaperDetailViewModel(
            wallpaperId = wallpaperId,
            photoRepository = photoRepository
        )
    }

    @AfterTest
    fun after()  { resetMain() }

    @Test
    fun `Initial ui state should be Loading` () {
        // Given
        val uiStateLoading  = WallpaperDetailUiState.Loading

        // When
        val currentUiState = viewModel.uiState.value

        // Then
        assertEquals(uiStateLoading, currentUiState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getWallpaper is called, then ui state should be Loading first and then should be Success` ()
    = scope(standardTestDispatcher).runTest {
        backgroundScope.launch { viewModel.uiState.collect() }
        // When
        viewModel.getWallpaper()

        // Then
        val uiStateLoading = viewModel.uiState.value
        advanceUntilIdle()
        val uiStateSuccess = viewModel.uiState.value
        assertTrue(uiStateLoading is WallpaperDetailUiState.Loading)
        assertTrue(uiStateSuccess is WallpaperDetailUiState.Success)
    }
}