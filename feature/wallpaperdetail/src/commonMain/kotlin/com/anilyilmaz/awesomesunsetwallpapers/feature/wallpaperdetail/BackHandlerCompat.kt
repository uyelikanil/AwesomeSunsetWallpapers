package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandlerCompat(enabled: Boolean, onBack: () -> Unit)