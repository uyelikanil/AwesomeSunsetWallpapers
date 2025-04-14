package com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.HomeRoute
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

fun NavGraphBuilder.homeScreen(
    sharedViewModel: SharedViewModel,
    onImageClick: (Int) -> Unit
) {
    composable<Home> {
        HomeRoute(
            sharedViewModel = sharedViewModel,
            onImageClick = onImageClick
        )
    }
}
