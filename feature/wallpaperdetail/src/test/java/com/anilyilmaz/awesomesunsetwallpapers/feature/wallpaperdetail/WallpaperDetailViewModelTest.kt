package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.lifecycle.SavedStateHandle
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WallpaperDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: WallpaperDetailViewModel
    private val dataSource = FakePexelsDataSource()
    private val dispatcher = StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler)
    private val photoRepository = PhotoRepositoryImpl(dataSource, dispatcher)
    private val getPhotoUseCase = GetPhotoUseCase(photoRepository)

    private val wallpaperId = 0

    @Before
    fun setUp() {
        val savedState = SavedStateHandle(mapOf("wallpaperId" to wallpaperId))
        viewModel = WallpaperDetailViewModel(savedState, getPhotoUseCase)
    }

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
    = runTest {
        // When
        viewModel.getWallpaper()

        // Then
        val uiStateLoading = viewModel.uiState.value
        advanceUntilIdle()
        val uiStateSuccess = viewModel.uiState.value
        assert(uiStateLoading is WallpaperDetailUiState.Loading)
        assert(uiStateSuccess is WallpaperDetailUiState.Success)
    }
}