package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.PhotoMapper
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network.FakePexelsDataSource
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPhotoUseCaseTest {

    private lateinit var usecase: GetPhotoUseCase
    private val photoMapper = PhotoMapper()
    private val pexelsDataSource = FakePexelsDataSource()
    private val dispatcher = UnconfinedTestDispatcher()
    private val photoRepository = PhotoRepositoryImpl(pexelsDataSource, dispatcher)

    @Before
    fun setUp() {
        usecase = GetPhotoUseCase(photoRepository, photoMapper)
    }

    @Test
    fun `given photo id, when getPhoto is called, then get seleceted PexelsPhoto with id as Photo`()
    = runTest {
        // Given
        val expectedPhoto = photoTestData(0)

        // When
        val result = usecase(0)

        // Then
        assertEquals(expectedPhoto, result)
    }
}