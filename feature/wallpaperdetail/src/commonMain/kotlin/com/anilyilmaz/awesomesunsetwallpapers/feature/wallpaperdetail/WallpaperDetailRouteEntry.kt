package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.compose.runtime.Composable

@Composable
expect fun WallpaperDetailRouteEntry(
    wallpaperId: Long,
    onNavigationClick: () -> Unit
)