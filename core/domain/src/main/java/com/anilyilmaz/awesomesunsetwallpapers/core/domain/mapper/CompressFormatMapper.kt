package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import android.graphics.Bitmap.CompressFormat
import javax.inject.Inject

class CompressFormatMapper @Inject constructor() {
    fun mapCompressFormatToSuffix(compressFormat: CompressFormat): String {
        return when(compressFormat) {
            CompressFormat.JPEG -> {".jpg"}
            CompressFormat.PNG -> {".png"}
            else -> {".webp"}
        }
    }
}