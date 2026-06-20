package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoListRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.LoadMoreSunsetPhotosUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.util.MainDispatcherBase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : MainDispatcherBase() {

    private lateinit var repository: ControlledPhotoRepository
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setUp() {
        installMain()
        repository = ControlledPhotoRepository()
        val getPhotos = GetSunsetPhotosUseCase(repository)
        viewModel = HomeViewModel(
            getSunsetPhotosUpdatedUseCase = getPhotos,
            loadMoreSunsetPhotosUseCase = LoadMoreSunsetPhotosUseCase(getPhotos)
        )
    }

    @AfterTest
    fun tearDown() {
        resetMain()
    }

    @Test
    fun `load more ignores second request and removes duplicate photos`() = runTest {
        val firstRequest = viewModel.loadMorePhotos()
        val secondRequest = viewModel.loadMorePhotos()

        assertEquals(listOf(1, 2), repository.requestedPages)

        repository.secondPage.complete(
            PhotoExpanded(
                totalResults = 90,
                page = 2,
                perPage = 30,
                photos = listOf(photoTestData(2), photoTestData(3))
            )
        )

        firstRequest.join()
        secondRequest.join()

        val photoIds = viewModel.uiState.value.photoExpanded
            ?.photos
            ?.map { it.id }

        assertEquals(listOf(1L, 2L, 3L), photoIds)
    }

    private class ControlledPhotoRepository : PhotoListRepository {
        val requestedPages = mutableListOf<Int>()
        val secondPage = CompletableDeferred<PhotoExpanded>()

        override suspend fun getPhotos(
            query: List<String>,
            page: Int,
            perPage: Int
        ): PhotoExpanded {
            requestedPages += page

            return if (page == 1) {
                createFirstPage(perPage)
            } else {
                secondPage.await()
            }
        }

        private fun createFirstPage(perPage: Int) = PhotoExpanded(
            totalResults = 90,
            page = 1,
            perPage = perPage,
            photos = listOf(photoTestData(1), photoTestData(2))
        )
    }
}
