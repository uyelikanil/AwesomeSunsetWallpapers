package com.anilyilmaz.awesomesunsetwallpapers.composeApp

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.ui.AwesomeSunsetWallpapersApp
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                /* clears the light‑icons flag */ 0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }

        setContent {
            AppTheme {
                AwesomeSunsetWallpapersApp()
            }
        }
    }
}