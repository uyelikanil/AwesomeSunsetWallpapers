package com.anilyilmaz.awesomesunsetwallpapers.composeApp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation.Home
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation.homeScreen
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.navigation.WallpaperDetail
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.navigation.wallpaperDetailScreen

@Composable
fun AwesomeSunsetWallpapersNavHost(
    navController: NavHostController,
    onShowMessage: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        homeScreen(
            onImageClick = { id -> navController.navigate(WallpaperDetail(wallpaperId = id))}
        )
        wallpaperDetailScreen(
            onNavigationClick = navController::popBackStack,
            onShowMessage = onShowMessage
        )
    }
}