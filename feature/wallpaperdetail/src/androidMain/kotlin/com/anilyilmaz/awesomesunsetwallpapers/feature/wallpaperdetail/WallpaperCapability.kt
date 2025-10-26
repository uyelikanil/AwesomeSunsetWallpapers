package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

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

class AndroidWallpaperCapability(
    private val context: Context,
    private val wallpaperManager: WallpaperManager,
    private val imageLoader: ImageLoader,
    private val setTempFileUseCase: SetTempFileUseCase
) : WallpaperCapability {
    override suspend fun performPrimaryAction(imageUrl: String) {
        val result = imageLoader.execute(ImageRequest.Builder(context).data(imageUrl).build())
        val bitmap: Bitmap = result.image?.toBitmap() ?: error("Image decode failed")

        val temp = setTempFileUseCase(
            bitmap,
            Bitmap.CompressFormat.JPEG,
            "wallpaper"
        )

        val uri: Uri = FileProvider.getUriForFile(
            context, "${context.packageName}.provider", temp
        )
        val intent: Intent = wallpaperManager
            .getCropAndSetWallpaperIntent(uri)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        context.startActivity(intent)
    }
}

actual fun defaultWallpaperCtaText(): String = "Set as wallpaper"