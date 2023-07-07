package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import android.graphics.Bitmap.CompressFormat
import org.junit.Assert.*
import org.junit.Test

class CompressFormatMapperTest {

    private val compressFormatMapper = CompressFormatMapper()

    @Test
    fun `given JPEG compress format, when mapCompressFormatToSuffix is called, then result should be suffix of jpg image format` () {
        //Given
        val jpgSuffix = ".jpg"

        // When
        val result = compressFormatMapper.mapCompressFormatToSuffix(CompressFormat.JPEG)

        // Then
        assertEquals(jpgSuffix, result)
    }
}