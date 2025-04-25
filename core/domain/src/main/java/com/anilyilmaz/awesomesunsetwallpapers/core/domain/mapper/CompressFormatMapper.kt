package com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper

import android.graphics.Bitmap.CompressFormat

fun CompressFormat.toSuffix(): String {
    return when(this) {
        CompressFormat.JPEG -> {".jpg"}
        CompressFormat.PNG -> {".png"}
        else -> {".webp"}
    }
}