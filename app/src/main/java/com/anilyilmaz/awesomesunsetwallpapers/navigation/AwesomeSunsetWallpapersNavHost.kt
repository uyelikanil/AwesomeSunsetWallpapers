package com.anilyilmaz.awesomesunsetwallpapers.navigation

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation.Home
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.navigation.WallpaperDetail
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation.homeScreen
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.navigation.wallpaperDetailScreen
import java.io.File

@Composable
fun AwesomeSunsetWallpapersNavHost(
    navController: NavHostController,
    getCropAndSetWallpaperIntent: (Uri) -> Intent,
    setTempImage: suspend (Bitmap, Bitmap.CompressFormat, String) -> File
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        homeScreen(
            onImageClick = { id -> navController.navigate(WallpaperDetail(wallpaperId = id))}
        )
        wallpaperDetailScreen(
            getCropAndSetWallpaperIntent = getCropAndSetWallpaperIntent,
            setTempImage = setTempImage,
            onNavigationClick = navController::popBackStack
        )
    }
}