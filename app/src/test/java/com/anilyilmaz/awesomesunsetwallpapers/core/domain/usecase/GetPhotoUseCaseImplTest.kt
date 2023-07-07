package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import androidx.paging.testing.asSnapshot
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.testdoubles.network.FakePexelsDataSource
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPhotoUseCaseImplTest {

    private lateinit var usecase: GetPhotoUseCaseImpl
    private val photoMapper = PhotoMapper()
    private val pexelsDataSource = FakePexelsDataSource()
    private val dispatcher = UnconfinedTestDispatcher()
    private val photoRepository = PhotoRepositoryImpl(pexelsDataSource, dispatcher)

    @Before
    fun setUp() {
        usecase = GetPhotoUseCaseImpl(photoRepository, photoMapper)
    }

    @Test
    fun `given photo id, when getPhoto is called, then get seleceted PexelsPhoto with id as Photo`()
    = runTest {
        // Given
        val expectedPhoto = photoTestData(0)

        // When
        val result = usecase.getPhoto(0)

        // Then
        assertEquals(expectedPhoto, result)
    }

    @Test
    fun `Items of paging data should be mapped to Photo`() = runTest {
        // Given
        val expectedPhoto = photoTestData()
        val query = listOf("sunset")
        val per_page = 1

        // When
        val result = usecase.getPhotos(query, per_page)
        val resultPhoto = result.asSnapshot().first()

        // Then
        assertEquals(expectedPhoto, resultPhoto)
    }
}