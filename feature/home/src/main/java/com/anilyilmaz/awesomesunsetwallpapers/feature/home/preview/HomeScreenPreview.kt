package com.anilyilmaz.awesomesunsetwallpapers.feature.home.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.model.PhotoExpanded
import com.anilyilmaz.awesomesunsetwallpapers.core.ui.HomeScreenPreviewParameterProvider
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.HomeScreen
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.HomeUiState
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.LoadState
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.LoadStatus

@Preview
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewParameterProvider::class)
    photos: List<Photo>
) {
    val photoExpanded = PhotoExpanded(
        totalResults = 8000,
        page = 1,
        perPage = 30,
        photos = photos.mapIndexed { index, photo ->
            photo.copy(isFavorite = index == 0)
        }
    )

    AppTheme {
        HomeScreen(
            uiState = HomeUiState(
                photoExpanded = photoExpanded,
                loadState = LoadState(
                    refresh = LoadStatus.NotLoading,
                    append = LoadStatus.NotLoading
                )
            ),
            refreshList = {},
            loadMoreItems = {},
            onImageClick = {},
            onFavoriteClick = {},
        )
    }
}
