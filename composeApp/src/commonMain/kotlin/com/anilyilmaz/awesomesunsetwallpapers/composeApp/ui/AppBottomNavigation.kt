package com.anilyilmaz.awesomesunsetwallpapers.composeApp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.FavoriteOutlineIcon
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.favorite
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.home
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppBottomNavigation(
    isHomeSelected: Boolean,
    onHomeClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    val homeContentDescription = stringResource(Res.string.home)
    val favoriteLabel = stringResource(Res.string.favorite)
    val itemColors = ShortNavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        selectedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
    )

    ShortNavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        ShortNavigationBarItem(
            selected = isHomeSelected,
            onClick = onHomeClick,
            colors = itemColors,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = homeContentDescription,
                )
            },
            label = { Text(homeContentDescription) },
        )
        ShortNavigationBarItem(
            selected = !isHomeSelected,
            onClick = onFavoriteClick,
            colors = itemColors,
            icon = {
                Icon(
                    imageVector = if (isHomeSelected) {
                        FavoriteOutlineIcon
                    } else {
                        Icons.Filled.Favorite
                    },
                    contentDescription = favoriteLabel,
                )
            },
            label = { Text(favoriteLabel) },
        )
    }
}
