package com.anilyilmaz.awesomesunsetwallpapers.core.data.mapper

import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.photoTestData
import kotlin.test.Test
import kotlin.test.assertEquals

class PhotoMapperTest {
    @Test
    fun `mapToPhoto maps PexelsPhoto to Photo`() {
        // Given
        val pexelsPhoto = pexelsPhotoTestData(0)
        val expectedPhoto = photoTestData(0)

        // When
        val result = pexelsPhoto.toPhoto()

        // Then
        assertEquals(expectedPhoto, result)
    }

    @Test
    fun `mapToPhotoExpanded maps PexelsPhotoExpanded to PhotoExpanded`() {
        // Given
        val pexelsPhotoExpanded = pexelsPhotoExpandedTestData()
        val expectedPhotoExpanded = photoExpandedTestData()

        // When
        val result = pexelsPhotoExpanded.toPhotoExpanded()

        // Then
        assertEquals(expectedPhotoExpanded, result)
    }
}
