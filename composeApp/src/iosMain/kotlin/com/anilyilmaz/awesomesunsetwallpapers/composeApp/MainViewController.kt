package com.anilyilmaz.awesomesunsetwallpapers.composeApp

import androidx.compose.ui.window.ComposeUIViewController
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.di.initKoin
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.ui.AwesomeSunsetWallpapersApp
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di.platformWallpaperDetailModule
import org.koin.compose.viewmodel.koinViewModel
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin(
        platformModules= listOf(platformWallpaperDetailModule)
    )

    return ComposeUIViewController {
        val sharedViewModel: SharedViewModel = koinViewModel()

        AppTheme {
            AwesomeSunsetWallpapersApp(
                initialNetworkState = NetworkState.AVAILABLE,
                sharedViewModel = sharedViewModel
            )
        }
    }
}