package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.toBitmap
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Android implementation of [WallpaperCapability].
 *
 * Flow:
 * 1. Load the wallpaper image from a String URL called [imageUrl].
 * 2. Write it to a temporary file via [SetTempFileUseCase].
 * 3. Create a content [Uri] with [FileProvider].
 * 4. Launch the system "crop & set wallpaper" UI via [WallpaperManager].
 * 5. Return:
 *    - [WallpaperCapabilityResult.Success] if the Intent was started successfully.
 *    - [WallpaperCapabilityResult.Error] if anything fails along the way.
 */
class AndroidWallpaperCapability(
    private val context: Context,
    private val wallpaperManager: WallpaperManager,
    private val imageLoader: ImageLoader,
    private val setTempFileUseCase: SetTempFileUseCase
) : WallpaperCapability {

    override suspend fun performPrimaryAction(imageUrl: String): WallpaperCapabilityResult =
        withContext(Dispatchers.IO) {
            try {
                val bitmap = loadBitmap(imageUrl)

                val tempFile = setTempFileUseCase(
                    bitmap,
                    Bitmap.CompressFormat.JPEG,
                    "wallpaper"
                )

                startCropAndSetWallpaper(tempFile)

                WallpaperCapabilityResult.Success
            } catch (t: Throwable) {
                WallpaperCapabilityResult.Error(t.message)
            }
        }
    private suspend fun loadBitmap(imageUrl: String): Bitmap {
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        val result = imageLoader.execute(request)

        return result.image
            ?.toBitmap()
            ?: error("Image decode failed")
    }

    private fun startCropAndSetWallpaper(tempFile: File) {
        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            tempFile
        )

        val intent: Intent = wallpaperManager
            .getCropAndSetWallpaperIntent(uri)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        context.startActivity(intent)
    }
}