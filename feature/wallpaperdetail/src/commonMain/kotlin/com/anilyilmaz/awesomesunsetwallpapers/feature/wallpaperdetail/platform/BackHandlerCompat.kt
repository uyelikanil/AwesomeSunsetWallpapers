package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandlerCompat(enabled: Boolean, onBack: () -> Unit)