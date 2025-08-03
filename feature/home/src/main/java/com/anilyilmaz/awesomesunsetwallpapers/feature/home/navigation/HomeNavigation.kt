package com.anilyilmaz.awesomesunsetwallpapers.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object Home

fun NavGraphBuilder.homeScreen(
    onImageClick: (Long) -> Unit
) {
    composable<Home> {
        HomeRoute(
            onImageClick = onImageClick
        )
    }
}
