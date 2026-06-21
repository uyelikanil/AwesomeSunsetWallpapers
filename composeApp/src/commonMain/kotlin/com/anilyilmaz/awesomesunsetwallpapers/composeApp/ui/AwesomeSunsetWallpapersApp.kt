package com.anilyilmaz.awesomesunsetwallpapers.composeApp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavDestination.Companion.hasRoute
import com.anilyilmaz.awesomesunsetwallpapers.composeApp.navigation.AwesomeSunsetWallpapersNavHost
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.network_is_connected
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.there_is_no_network
import com.anilyilmaz.awesomesunsetwallpapers.core.system.NetworkMonitor
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import com.anilyilmaz.awesomesunsetwallpapers.feature.favorite.navigation.Favorite
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation.Home
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AwesomeSunsetWallpapersApp(
    sharedViewModel: SharedViewModel = koinViewModel(),
    networkMonitor: NetworkMonitor = koinInject()
) {
    val rawNetworkState by networkMonitor.state.collectAsStateWithLifecycle()
    val networkState by sharedViewModel.networkState.collectAsStateWithLifecycle(NetworkState.AVAILABLE)

    LaunchedEffect(rawNetworkState) {
        sharedViewModel.updateNetworkState(rawNetworkState)
    }

    Surface(Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val destination = backStackEntry?.destination
        val isHomeSelected = destination?.hasRoute<Home>() == true
        val isFavoriteSelected = destination?.hasRoute<Favorite>() == true
        val showBottomBar = isHomeSelected || isFavoriteSelected
        val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                if (showBottomBar) {
                    AppBottomNavigation(
                        isHomeSelected = isHomeSelected,
                        onHomeClick = {
                            navController.navigate(Home) {
                                popUpTo(Home) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onFavoriteClick = {
                            navController.navigate(Favorite) {
                                popUpTo(Home) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            },
            contentWindowInsets = WindowInsets(0)
        ) { paddingValues ->
            val networkIsConnectedMessage = stringResource(Res.string.network_is_connected)
            val thereIsNoNetworkMessage = stringResource(Res.string.there_is_no_network)
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
                        message = thereIsNoNetworkMessage,
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
                navController = navController,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}
