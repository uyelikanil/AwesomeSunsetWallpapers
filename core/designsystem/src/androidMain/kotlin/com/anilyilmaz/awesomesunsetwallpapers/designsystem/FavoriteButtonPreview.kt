package com.anilyilmaz.awesomesunsetwallpapers.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.FavoriteButton
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.FavoriteIcon
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun FavoriteButtonPreview() {
    AppTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            FavoriteButton(
                isFavorite = false,
                contentDescription = "Favorite wallpaper",
                onClick = {},
            )
            FavoriteButton(
                isFavorite = true,
                contentDescription = "Favorite wallpaper",
                onClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteIconPreview() {
    AppTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            FavoriteIcon(
                isFavorite = false,
                contentDescription = "Favorite wallpaper",
            )
            FavoriteIcon(
                isFavorite = true,
                contentDescription = "Favorite wallpaper",
            )
        }
    }
}
