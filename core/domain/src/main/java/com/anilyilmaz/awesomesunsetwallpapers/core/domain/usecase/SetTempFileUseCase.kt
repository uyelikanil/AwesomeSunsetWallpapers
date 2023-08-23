package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import android.graphics.Bitmap
import java.io.File

interface SetTempFileUseCase {

    suspend fun setTempImage(imageBitmap: Bitmap, compressFormat: Bitmap.CompressFormat,
                            fileName: String): File

}