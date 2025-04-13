package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import android.graphics.Bitmap.CompressFormat
import org.junit.Assert.*
import org.junit.Test

class CompressFormatMapperTest {
    @Test
    fun `given JPEG compress format, when mapCompressFormatToSuffix is called, then result should be suffix of jpg image format` () {
        //Given
        val jpgSuffix = ".jpg"

        // When
        val result = CompressFormat.JPEG.toSuffix()

        // Then
        assertEquals(jpgSuffix, result)
    }
}