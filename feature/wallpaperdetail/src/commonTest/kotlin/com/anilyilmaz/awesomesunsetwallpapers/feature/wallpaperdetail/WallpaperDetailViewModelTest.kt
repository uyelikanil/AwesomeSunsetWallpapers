package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util.MainDispatcherBase
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.testdouble.FakeWallpaperCapability
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WallpaperDetailViewModelTest : MainDispatcherBase() {

    private lateinit var viewModel: WallpaperDetailViewModel
    private lateinit var capability: FakeWallpaperCapability
    private val dataSource = FakePexelsDataSource(standardTestDispatcher)
    private val photoRepository = PhotoRepositoryImpl(dataSource, standardTestDispatcher)
    private val wallpaperId = 0L

    @BeforeTest
    fun before() {
        installMain()
    }

    @BeforeTest
    fun setUp() {
        capability = FakeWallpaperCapability()

        viewModel = WallpaperDetailViewModel(
            wallpaperId = wallpaperId,
            photoRepository = photoRepository,
            capability = capability,
            loadOnInit = false
        )
    }

    @AfterTest
    fun after() {
        resetMain()
    }

    @Test
    fun `Initial ui state should be Loading` () {
        // Given
        val uiStateLoading = WallpaperDetailUiState.Loading

        // When
        val currentUiState = viewModel.uiState.value

        // Then
        assertEquals(uiStateLoading, currentUiState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getWallpaper is called, then ui state should be Loading first and then should be Success` () =
        scope(standardTestDispatcher).runTest {
            // When
            viewModel.getWallpaper()

            // Then
            assertTrue(viewModel.uiState.value is WallpaperDetailUiState.Loading)

            advanceUntilIdle()

            assertTrue(viewModel.uiState.value is WallpaperDetailUiState.Success)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onWallpaperAction should emit Loading then Success when capability succeeds`() =
        scope(standardTestDispatcher).runTest {
            // Given
            viewModel.getWallpaper()
            advanceUntilIdle()
            assertTrue(viewModel.uiState.value is WallpaperDetailUiState.Success)

            // When
            viewModel.onWallpaperAction()

            // Then
            assertTrue(viewModel.capabilityState.value is WallpaperDetailCapabilityState.Loading)

            capability.completeSuccess()
            advanceUntilIdle()

            val finalState = viewModel.capabilityState.value
            assertTrue(finalState is WallpaperDetailCapabilityState.Success)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onWallpaperAction should emit Loading then Error when capability fails`() =
        scope(standardTestDispatcher).runTest {
            // Given
            viewModel.getWallpaper()
            advanceUntilIdle()
            assertTrue(viewModel.uiState.value is WallpaperDetailUiState.Success)

            // When
            viewModel.onWallpaperAction()

            // Then
            advanceUntilIdle()
            assertTrue(viewModel.capabilityState.value is WallpaperDetailCapabilityState.Loading)

            capability.completeError("error")
            advanceUntilIdle()
            assertTrue(viewModel.capabilityState.value is WallpaperDetailCapabilityState.Error)
        }
}