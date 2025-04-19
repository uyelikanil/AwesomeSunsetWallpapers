package com.anilyilmaz.awesomesunsetwallpapers

import android.app.WallpaperManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import com.anilyilmaz.awesomesunsetwallpapers.ui.AwesomeSunsetWallpapersApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var connectivityManager: ConnectivityManager
    @Inject lateinit var wallpaperManager: WallpaperManager
    @Inject lateinit var getNetworkStateUseCase: GetNetworkStateUseCase
    @Inject lateinit var setTempFileUseCase: SetTempFileUseCase
    private val sharedViewModel by viewModels<SharedViewModel>()

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
                AwesomeSunsetWallpapersApp(
                    sharedViewModel = sharedViewModel,
                    connectivityManager = connectivityManager,
                    wallpaperManager = wallpaperManager,
                    getNetworkStateUseCase = getNetworkStateUseCase,
                    setTempFileUseCase = setTempFileUseCase
                )
            }
        }

        setInternetConnection()
    }

    private fun setInternetConnection () {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                sharedViewModel.updateNetworkState(NetworkState.AVAILABLE)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                sharedViewModel.updateNetworkState(NetworkState.LOST)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

