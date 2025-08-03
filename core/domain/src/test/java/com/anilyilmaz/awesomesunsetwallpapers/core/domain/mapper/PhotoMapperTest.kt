package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoMapperTest {
    @Test
    fun `given PexelsPhoto, when mapToPhoto is called, then PexelsPhoto should be mapped to Photo`() {
        // Given
        val pexelsPhoto = pexelsPhotoTestData(0)
        val expectedPhoto = photoTestData(0)

        // When
        val result = pexelsPhoto.toPhoto()

        // Then
        assertEquals(expectedPhoto, result)
    }

    @Test
    fun `given PexelsPhotoExpanded, when mapToPhotoExpanded is called, then PexelsPhotoExpanded should be mapped to PhotoExpanded`() {
        // Given
        val pexelsPhotoExpanded = pexelsPhotoExpandedTestData()
        val expectedPhotoExpanded = photoExpandedTestData()

        // When
        val result = pexelsPhotoExpanded.toPhotoExpanded()

        // Then
        assertEquals(expectedPhotoExpanded, result)
    }
}