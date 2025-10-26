package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme

@Preview
@Composable
fun WallpaperDetailScreenPreview() {
    AppTheme {
        WallpaperDetailScreen(
            context = LocalContext.current,
            uiState = WallpaperDetailUiState.Success(
                wallpaperSrc = "https://images.pexels.com/photos/848573/pexels-photo-848573.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                photographer = "ANIL"),
            ctaText = "Set as a Wallpaper",
            onPrimaryAction = {},
            onNavigationClick = {},
            onRetry = {}
        )
    }
}