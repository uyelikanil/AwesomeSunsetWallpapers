package com.anilyilmaz.awesomesunsetwallpapers.feature.favorite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anilyilmaz.awesomesunsetwallpapers.feature.favorite.FavoriteRoute
import kotlinx.serialization.Serializable

@Serializable
object Favorite

fun NavGraphBuilder.favoriteScreen(
    onImageClick: (Long) -> Unit,
) {
    composable<Favorite> {
        FavoriteRoute(onImageClick = onImageClick)
    }
}
