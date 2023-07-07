package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import androidx.paging.testing.asSnapshot
import com.anilyilmaz.awesomesunsetwallpapers.core.data.FakePhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.core.modelfactory.photoTestData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPhotoUseCaseImplTest {

    private lateinit var usecase: GetPhotoUseCaseImpl
    private val photoRepository = FakePhotoRepositoryImpl()
    private val photoMapper = PhotoMapper()

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