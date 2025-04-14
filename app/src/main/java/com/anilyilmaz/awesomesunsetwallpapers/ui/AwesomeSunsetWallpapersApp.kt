package com.anilyilmaz.awesomesunsetwallpapers.ui

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.net.ConnectivityManager
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.anilyilmaz.awesomesunsetwallpapers.R
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import com.anilyilmaz.awesomesunsetwallpapers.navigation.AwesomeSunsetWallpapersNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AwesomeSunsetWallpapersApp(
    sharedViewModel: SharedViewModel,
    connectivityManager: ConnectivityManager,
    wallpaperManager: WallpaperManager,
    getNetworkStateUseCase: GetNetworkStateUseCase,
    setTempFileUseCase: SetTempFileUseCase
) {
    val navController = rememberNavController()

    val initialNetworkState = getInitialNetworkState(connectivityManager = connectivityManager,
        getNetworkStateUseCase = getNetworkStateUseCase)
    val networkState by sharedViewModel.networkState.collectAsStateWithLifecycle(
        initialValue = initialNetworkState)

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        val networkIsConnectedMessage = stringResource(id = R.string.network_is_connected)
        val thereIsNoNetworkdMessage = stringResource(id = R.string.there_is_no_network)
        LaunchedEffect(networkState) {
            if (networkState == NetworkState.CONNECTED) {
                snackbarHostState.showSnackbar(
                    message = networkIsConnectedMessage,
                    duration = SnackbarDuration.Short
                )
            } else if (networkState == NetworkState.LOST ||
                networkState == NetworkState.UNAVAILABLE) {
                snackbarHostState.showSnackbar(
                    message = thereIsNoNetworkdMessage,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

        AwesomeSunsetWallpapersNavHost(
            navController = navController,
            sharedViewModel = sharedViewModel,
            getCropAndSetWallpaperIntent = wallpaperManager::getCropAndSetWallpaperIntent,
            setTempImage = setTempFileUseCase::invoke
        )
    }
}

private fun getInitialNetworkState(
    connectivityManager: ConnectivityManager,
    getNetworkStateUseCase: GetNetworkStateUseCase
): NetworkState {
    val isThereActiveNetwork = connectivityManager.activeNetwork != null
    val initialNetworkState = getNetworkStateUseCase(isThereActiveNetwork)
    return initialNetworkState
}
