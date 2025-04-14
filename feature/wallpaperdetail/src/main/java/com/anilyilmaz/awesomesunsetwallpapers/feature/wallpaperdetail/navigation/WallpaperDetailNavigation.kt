package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.navigation

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailRoute
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class WallpaperDetail(val wallpaperId: Int)

fun NavGraphBuilder.wallpaperDetailScreen(
    getCropAndSetWallpaperIntent: (Uri) -> Intent,
    setTempImage: suspend (Bitmap, Bitmap.CompressFormat, String) -> File,
    onNavigationClick: () -> Unit
) {
    composable<WallpaperDetail> {
        WallpaperDetailRoute(
            getCropAndSetWallpaperIntent = getCropAndSetWallpaperIntent,
            setTempImage = setTempImage,
            onNavigationClick = onNavigationClick
        )
    }
}
