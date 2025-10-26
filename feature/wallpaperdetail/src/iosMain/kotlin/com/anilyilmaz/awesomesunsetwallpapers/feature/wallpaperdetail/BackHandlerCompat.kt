package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandlerCompat(enabled: Boolean, onBack: () -> Unit) {
    // no-op; wire to your nav back if you have gestures
}