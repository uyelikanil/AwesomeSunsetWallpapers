package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import com.anilyilmaz.awesomesunsetwallpapers.core.data.FakePhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCaseImpl
import com.anilyilmaz.awesomesunsetwallpapers.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WallpaperDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: WallpaperDetailViewModel
    private lateinit var getPhotoUseCase : GetPhotoUseCaseImpl
    private val photoRepository = FakePhotoRepositoryImpl()
    private val photoMapper = PhotoMapper()

    @Before
    fun setUp() {
        getPhotoUseCase = GetPhotoUseCaseImpl(photoRepository, photoMapper)
        viewModel = WallpaperDetailViewModel(getPhotoUseCase)
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

    @Test
    fun `when getWallpaper is called, then ui state should be Loading first and then should be Success` ()
    = runTest {
        // Given
        val wallpaperId = 0

        val job = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect()
        }

        // When
        viewModel.getWallpaper(wallpaperId)

        // Then
        var uiState = viewModel.uiState.value
        launch {
            assert(uiState is WallpaperDetailUiState.Loading)
            uiState = viewModel.uiState.value
            assert(uiState is WallpaperDetailUiState.Success)
            job.cancel()
        }
    }
}