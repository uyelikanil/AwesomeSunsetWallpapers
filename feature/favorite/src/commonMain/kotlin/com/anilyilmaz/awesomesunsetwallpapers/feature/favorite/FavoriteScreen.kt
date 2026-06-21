package com.anilyilmaz.awesomesunsetwallpapers.feature.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.favorite
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.favorite_wallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.no_favorite_wallpapers
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.FavoriteIcon
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.WallpaperGridItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteRoute(
    onImageClick: (Long) -> Unit,
    viewModel: FavoriteViewModel = koinViewModel(),
) {
    val favoriteWallpapers by viewModel.favoriteWallpapers.collectAsStateWithLifecycle()
    FavoriteScreen(
        favoriteWallpapers = favoriteWallpapers,
        onImageClick = onImageClick,
        onFavoriteClick = viewModel::toggleFavorite,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavoriteScreen(
    favoriteWallpapers: List<Photo>,
    onImageClick: (Long) -> Unit,
    onFavoriteClick: (Photo) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(Res.string.favorite)) }
            )
        }
    ) { paddingValues ->
        if (favoriteWallpapers.isEmpty()) {
            EmptyFavorite(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        } else {
            FavoriteWallpaperGrid(
                favoriteWallpapers = favoriteWallpapers,
                paddingValues = paddingValues,
                onImageClick = onImageClick,
                onFavoriteClick = onFavoriteClick,
            )
        }
    }
}

@Composable
private fun FavoriteWallpaperGrid(
    favoriteWallpapers: List<Photo>,
    paddingValues: PaddingValues,
    onImageClick: (Long) -> Unit,
    onFavoriteClick: (Photo) -> Unit,
) {
    val favoriteContentDescription = stringResource(Res.string.favorite_wallpaper)

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
    ) {
        items(
            count = favoriteWallpapers.size,
            key = { index -> favoriteWallpapers[index].id },
        ) { index ->
            val favorite = favoriteWallpapers[index]
            WallpaperGridItem(
                imageUrl = favorite.src.portrait,
                isFavorite = favorite.isFavorite,
                favoriteContentDescription = favoriteContentDescription,
                onClick = { onImageClick(favorite.id) },
                onFavoriteClick = { onFavoriteClick(favorite) },
            )
        }
    }
}

@Composable
private fun EmptyFavorite(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        FavoriteIcon(
            isFavorite = false,
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(72.dp),
            unselectedTint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = stringResource(Res.string.no_favorite_wallpapers),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp),
        )
    }
}
