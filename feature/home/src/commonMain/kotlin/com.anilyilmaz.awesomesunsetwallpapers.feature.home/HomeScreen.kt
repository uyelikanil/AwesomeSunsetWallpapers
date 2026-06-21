package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.extension.shimmerEffect
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.app_name
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.favorite_wallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.retry
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.something_went_wrong
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.WallpaperGridItem
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel(),
    onImageClick: (Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        refreshList = viewModel::getPhotos,
        loadMoreItems = viewModel::loadMorePhotos,
        onImageClick = onImageClick,
        onFavoriteClick = viewModel::toggleFavorite,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    refreshList: () -> Unit,
    loadMoreItems: () -> Unit,
    onImageClick: (Long) -> Unit,
    onFavoriteClick: (Photo) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.wrapContentSize(Alignment.TopStart),
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surface),
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        PagingList(
            paddingValues = it,
            uiState = uiState,
            refreshList = refreshList,
            loadMoreItems = loadMoreItems,
            onImageClick = onImageClick,
            onFavoriteClick = onFavoriteClick,
        )
    }
}

@Composable
private fun PagingList(
    paddingValues: PaddingValues,
    uiState: HomeUiState,
    refreshList: () -> Unit,
    loadMoreItems: () -> Unit,
    onImageClick: (Long) -> Unit,
    onFavoriteClick: (Photo) -> Unit,
) {
    val gridState = rememberLazyGridState()
    val favoriteContentDescription = stringResource(Res.string.favorite_wallpaper)

    LaunchedEffect(gridState) {
        snapshotFlow {
            val lastVisible = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val total = gridState.layoutInfo.totalItemsCount
            lastVisible != null && total - lastVisible <= 6
        }
            .distinctUntilChanged()
            .collect { shouldLoad -> if (shouldLoad) loadMoreItems() }
    }

    Crossfade(uiState.loadState.refresh) { refreshState ->
        when(refreshState) {
            is LoadStatus.Loading -> {
                CircularProgressIndicator(modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                )
            }
            is LoadStatus.NotLoading -> {
                uiState.photoExpanded?.run {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp),
                        state = gridState,
                        contentPadding = PaddingValues(4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
                    ) {
                        items(
                            count = photos.count(),
                            key = { idx -> photos[idx].id }
                        ) {
                            val item = photos[it]
                            WallpaperGridItem(
                                imageUrl = item.src.portrait,
                                isFavorite = item.isFavorite,
                                favoriteContentDescription = favoriteContentDescription,
                                onClick = { onImageClick(item.id) },
                                onFavoriteClick = { onFavoriteClick(item) },
                                loadingPlaceholder = {
                                    Spacer(
                                        Modifier
                                            .fillMaxSize()
                                            .shimmerEffect()
                                    )
                                },
                            )
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            if(uiState.loadState.append == LoadStatus.Loading) {
                                CircularProgressIndicator(modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .wrapContentSize(Alignment.BottomCenter)
                                    .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
            is LoadStatus.Error -> {
                ErrorLayout(refreshList = refreshList)
            }
        }
    }
}

@Composable
private fun ErrorLayout(
    refreshList: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(Res.string.something_went_wrong),
            modifier = Modifier.padding(bottom = 10.dp),
        )
        Text(
            text = stringResource(Res.string.retry),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                refreshList()
            }
        )
    }
}
