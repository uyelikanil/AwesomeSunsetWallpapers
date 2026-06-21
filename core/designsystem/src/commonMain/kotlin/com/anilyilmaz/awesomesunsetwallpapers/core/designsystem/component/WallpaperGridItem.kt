package com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun WallpaperGridItem(
    imageUrl: String,
    isFavorite: Boolean,
    favoriteContentDescription: String,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    loadingPlaceholder: @Composable () -> Unit = { Spacer(Modifier.fillMaxSize()) },
) {
    val platformContext = LocalPlatformContext.current

    Box(
        modifier = modifier
            .aspectRatio(0.75f)
            .clip(ShapeDefaults.Medium),
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(platformContext)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick),
            loading = { loadingPlaceholder() },
            error = { loadingPlaceholder() },
        )

        FavoriteButton(
            isFavorite = isFavorite,
            contentDescription = favoriteContentDescription,
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp),
        )
    }
}
