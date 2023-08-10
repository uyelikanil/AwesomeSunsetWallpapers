package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import android.graphics.Bitmap.CompressFormat
import org.junit.Assert.*
import org.junit.Test

class CompressFormatMapperTest {
    private val compressFormatMapper = CompressFormatMapper()

    // TODO: After update compileSdk to 34, CompressFormat.JPEG can't be mocked
    //  Until it's fixed, mapCompressFormatToSuffix won't be tested
    /*@Test
    fun `given JPEG compress format, when mapCompressFormatToSuffix is called, then result should be suffix of jpg image format` () {
        //Given
        val jpgSuffix = ".jpg"

        // When
        val result = compressFormatMapper.mapCompressFormatToSuffix(CompressFormat.JPEG)

        // Then
        assertEquals(jpgSuffix, result)
    }*/
}