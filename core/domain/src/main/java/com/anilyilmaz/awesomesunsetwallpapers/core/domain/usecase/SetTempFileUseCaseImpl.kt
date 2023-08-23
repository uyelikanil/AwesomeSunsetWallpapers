package com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import com.anilyilmaz.awesomesunsetwallpapers.core.common.AswDispatchers
import com.anilyilmaz.awesomesunsetwallpapers.core.common.Dispatcher
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.mapper.CompressFormatMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class SetTempFileUseCaseImpl @Inject constructor(
    @Dispatcher(AswDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val compressFormatMapper: CompressFormatMapper):
    SetTempFileUseCase {

    override suspend fun setTempImage(imageBitmap: Bitmap, compressFormat: CompressFormat,
        fileName: String): File = withContext(ioDispatcher) {
        val bytes = ByteArrayOutputStream()
        imageBitmap.compress(compressFormat, 100, bytes)
        val bitmapData = bytes.toByteArray()

        val suffix: String = compressFormatMapper.mapCompressFormatToSuffix(compressFormat)
        val tempFile = File.createTempFile(fileName, suffix)
        val fileOutPut = FileOutputStream(tempFile)
        fileOutPut.write(bitmapData)
        fileOutPut.flush()
        fileOutPut.close()
        tempFile
    }
}