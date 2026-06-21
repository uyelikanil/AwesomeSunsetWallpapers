package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetWallpaperUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.data.FakeFavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util.MainDispatcherBase
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.testdouble.FakeWallpaperCapability
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first

class WallpaperDetailViewModelTest : MainDispatcherBase() {

    private lateinit var viewModel: WallpaperDetailViewModel
    private lateinit var capability: FakeWallpaperCapability
    private lateinit var favoriteRepository: FakeFavoriteWallpaperRepository
    private lateinit var photoResult: CompletableDeferred<Photo>
    private val wallpaperId = 0L

    @BeforeTest
    fun setUp() {
        installMain()
        capability = FakeWallpaperCapability()
        favoriteRepository = FakeFavoriteWallpaperRepository()
        photoResult = CompletableDeferred()

        viewModel = WallpaperDetailViewModel(
            wallpaperId = wallpaperId,
            getWallpaperUseCase = GetWallpaperUseCase(
                photoRepository = object : PhotoRepository {
                    override suspend fun getPhoto(id: Long) = photoResult.await()
                },
                favoriteWallpaperRepository = favoriteRepository,
            ),
            favoriteWallpaperRepository = favoriteRepository,
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
    fun `getWallpaper emits Loading then Success` () =
        scope(standardTestDispatcher).runTest {
            // When
            viewModel.getWallpaper()

            // Then
            assertTrue(viewModel.uiState.value is WallpaperDetailUiState.Loading)

            photoResult.complete(photoTestData(wallpaperId))
            advanceUntilIdle()

            assertTrue(viewModel.uiState.value is WallpaperDetailUiState.Success)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onWallpaperAction should emit Loading then Success when capability succeeds`() =
        scope(standardTestDispatcher).runTest {
            // Given
            viewModel.getWallpaper()
            photoResult.complete(photoTestData(wallpaperId))
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
            photoResult.complete(photoTestData(wallpaperId))
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggle favorite saves loaded wallpaper`() =
        scope(standardTestDispatcher).runTest {
            viewModel.getWallpaper()
            photoResult.complete(photoTestData(wallpaperId))
            advanceUntilIdle()

            viewModel.toggleFavorite()
            advanceUntilIdle()

            assertTrue(favoriteRepository.observeIsFavorite(wallpaperId).first())
            assertEquals(
                true,
                (viewModel.uiState.value as WallpaperDetailUiState.Success).photo.isFavorite,
            )
        }
}
