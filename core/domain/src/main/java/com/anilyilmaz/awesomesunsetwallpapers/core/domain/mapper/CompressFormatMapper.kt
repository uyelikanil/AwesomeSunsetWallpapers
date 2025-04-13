package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import android.graphics.Bitmap.CompressFormat
import javax.inject.Inject

fun CompressFormat.toSuffix(): String {
    return when(this) {
        CompressFormat.JPEG -> {".jpg"}
        CompressFormat.PNG -> {".png"}
        else -> {".webp"}
    }
}