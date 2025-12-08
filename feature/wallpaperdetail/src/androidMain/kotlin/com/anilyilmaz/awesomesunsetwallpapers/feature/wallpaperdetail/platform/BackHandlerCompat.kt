package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun BackHandlerCompat(enabled: Boolean, onBack: () -> Unit) {
    BackHandler(enabled, onBack)
}