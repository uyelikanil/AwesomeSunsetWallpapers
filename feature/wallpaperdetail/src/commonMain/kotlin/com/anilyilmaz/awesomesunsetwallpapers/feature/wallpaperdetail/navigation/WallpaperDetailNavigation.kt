package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class WallpaperDetail(val wallpaperId: Long)

fun NavGraphBuilder.wallpaperDetailScreen(
    onNavigationClick: () -> Unit
) {
    composable<WallpaperDetail> { backStackEntry ->
        val args = backStackEntry.toRoute<WallpaperDetail>() // JetBrains navigation typed args
        WallpaperDetailRoute(
            wallpaperId = args.wallpaperId,
            onNavigationClick = onNavigationClick
        )
    }
}
