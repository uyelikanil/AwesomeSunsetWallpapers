package com.anilyilmaz.awesomesunsetwallpapers.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.WallpaperGridItem
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun WallpaperGridItemPreview() {
    AppTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            PreviewWallpaperGridItem(isFavorite = false)
            PreviewWallpaperGridItem(isFavorite = true)
        }
    }
}

@Composable
private fun PreviewWallpaperGridItem(
    isFavorite: Boolean,
) {
    WallpaperGridItem(
        imageUrl = "",
        isFavorite = isFavorite,
        favoriteContentDescription = "Favorite wallpaper",
        onClick = {},
        onFavoriteClick = {},
        modifier = Modifier.width(128.dp),
        loadingPlaceholder = {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.72f),
                                MaterialTheme.colorScheme.surface,
                            )
                        )
                    ),
            )
        },
    )
}
