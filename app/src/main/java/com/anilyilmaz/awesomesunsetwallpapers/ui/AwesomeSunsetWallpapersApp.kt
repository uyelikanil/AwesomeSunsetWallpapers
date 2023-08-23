package com.anilyilmaz.awesomesunsetwallpapers.ui

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anilyilmaz.awesomesunsetwallpapers.R
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetNetworkStateUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.HomeRoute
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailRoute
import java.io.File

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
            setTempImage = setTempFileUseCase::setTempImage
        )
    }
}

private fun getInitialNetworkState(
    connectivityManager: ConnectivityManager,
    getNetworkStateUseCase: GetNetworkStateUseCase
): NetworkState {
    val isThereActiveNetwork = connectivityManager.activeNetwork != null
    val initialNetworkState = getNetworkStateUseCase.getState(isThereActiveNetwork)
    return initialNetworkState
}

@Composable
private fun AwesomeSunsetWallpapersNavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    getCropAndSetWallpaperIntent: (Uri) -> Intent,
    setTempImage: suspend (Bitmap, Bitmap.CompressFormat, String) -> File
) {
    NavHost(
        navController = navController,
        startDestination = "home") {
        composable(route = "home") {
            HomeRoute(
                sharedViewModel = sharedViewModel,
                onImageClick = { id -> navController.navigate("wallpaperDetail/$id") }
            )
        }
        composable(
            route = "wallpaperDetail/{wallpaperId}",
            arguments = listOf(navArgument("wallpaperId") { type = NavType.IntType }))
        {
            WallpaperDetailRoute(
                getCropAndSetWallpaperIntent = getCropAndSetWallpaperIntent,
                setTempImage = setTempImage,
                onNavigationClick = navController::popBackStack
            )
        }
    }
}