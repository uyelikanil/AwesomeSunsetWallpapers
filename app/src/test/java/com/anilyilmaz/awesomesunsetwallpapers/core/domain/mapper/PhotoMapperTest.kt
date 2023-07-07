package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory.pexelsPhotoTestData
import com.anilyilmaz.awesomesunsetwallpapers.testdoubles.modelfactory.photoTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoMapperTest {

    private val photoMapper = PhotoMapper()

    @Test
    fun `given PexelsPhoto, when mapToPhoto is called, then PexelsPhoto should be mapped to Photo` () {
        // Given
        val pexelsPhoto = pexelsPhotoTestData(0)
        val expectedPhoto = photoTestData(0)

        // When
        val result = photoMapper.mapToPhoto(pexelsPhoto)

        // Then
        assertEquals(expectedPhoto, result)
    }
}