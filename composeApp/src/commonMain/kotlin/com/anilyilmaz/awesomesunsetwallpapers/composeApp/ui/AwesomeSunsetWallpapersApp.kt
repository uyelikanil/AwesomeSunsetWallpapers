package com.anilyilmaz.awesomesunsetwallpapers.composeApp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.navigation.AwesomeSunsetWallpapersNavHost
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.network_is_connected
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.there_is_no_network
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun AwesomeSunsetWallpapersApp(
    sharedViewModel: SharedViewModel,
    initialNetworkState: NetworkState,
) {
    val networkState by sharedViewModel.networkState.collectAsStateWithLifecycle(
        initialValue = initialNetworkState
    )

    Surface(Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            contentWindowInsets = ScaffoldDefaults.contentWindowInsets
        ) {
            val networkIsConnectedMessage = stringResource(Res.string.network_is_connected)
            val thereIsNoNetworkdMessage = stringResource(Res.string.there_is_no_network)
            LaunchedEffect(networkState) {
                if (networkState == NetworkState.CONNECTED) {
                    snackbarHostState.showSnackbar(
                        message = networkIsConnectedMessage,
                        duration = SnackbarDuration.Short
                    )
                } else if (networkState == NetworkState.LOST ||
                    networkState == NetworkState.UNAVAILABLE
                ) {
                    snackbarHostState.showSnackbar(
                        message = thereIsNoNetworkdMessage,
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }

            AwesomeSunsetWallpapersNavHost(
                onShowMessage = { message ->
                    scope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                },
                navController = navController
            )
        }
    }
}