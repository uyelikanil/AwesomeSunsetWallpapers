package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.network.model.PexelsPhotoSrc

@Preview
@Composable
fun WallpaperDetailScreenPreview() {
    AppTheme {
        WallpaperDetailScreen(
            context = LocalContext.current,
            uiState = WallpaperDetailUiState.Success(
                photo = Photo(
                    id = 848573,
                    photographer = "ANIL",
                    src = PexelsPhotoSrc(
                        portrait = "https://images.pexels.com/photos/848573/pexels-photo-848573.jpeg?" +
                            "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                    ),
                ),
            ),
            capabilityState = WallpaperDetailCapabilityState.Init,
            onPrimaryAction = {},
            onPrimaryActionHandled = {},
            onFavoriteClick = {},
            onRetry = {},
            onNavigationClick = {},
            onShowMessage = {}
        )
    }
}
