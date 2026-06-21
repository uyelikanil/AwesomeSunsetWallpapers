package com.anilyilmaz.awesomesunsetwallpapers.composeApp

import androidx.compose.ui.window.ComposeUIViewController
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.di.initKoin
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.ui.AwesomeSunsetWallpapersApp
import com.anilyilmaz.awesomesunsetwallpapers.core.local.di.localPlatformModule
import com.anilyilmaz.awesomesunsetwallpapers.core.system.di.systemIosModule
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di.platformWallpaperDetailModule
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin(
        platformModules = listOf(
            systemIosModule,
            localPlatformModule,
            platformWallpaperDetailModule
        )
    )

    return ComposeUIViewController {
        AppTheme {
            AwesomeSunsetWallpapersApp()
        }
    }
}
