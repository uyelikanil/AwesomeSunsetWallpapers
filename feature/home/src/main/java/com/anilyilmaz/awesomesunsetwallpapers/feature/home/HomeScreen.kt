package com.anilyilmaz.awesomesunsetwallpapers.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.core.model.NetworkState
import com.anilyilmaz.awesomesunsetwallpapers.core.model.Photo
import com.anilyilmaz.awesomesunsetwallpapers.core.ui.HomeScreenPreviewParameterProvider
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.SharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    onImageClick: (Int) -> Unit
) {
    val networkState by sharedViewModel.networkState.collectAsStateWithLifecycle(
        initialValue = NetworkState.AVAILABLE)
    val wallpapers = viewModel.getWallpapers.collectAsLazyPagingItems()

    HomeScreen(
        wallpapers = wallpapers,
        networkState = networkState,
        onImageClick = onImageClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    wallpapers: LazyPagingItems<Photo>,
    networkState: NetworkState,
    onImageClick: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.wrapContentSize(Alignment.TopStart),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surface),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        PagingList(
            paddingValues = it,
            wallpapers = wallpapers,
            onImageClick = onImageClick
        )
    }

    if(networkState == NetworkState.CONNECTED) {
        wallpapers.refresh()
    }
}

@Composable
private fun PagingList(
    paddingValues: PaddingValues,
    wallpapers: LazyPagingItems<Photo>,
    onImageClick: (Int) -> Unit
) {
    when(wallpapers.loadState.refresh) {
        is LoadState.Loading -> {
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
            )
        }
        is LoadState.NotLoading -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
            ) {
                items(
                    wallpapers.itemCount,
                    key = wallpapers.itemKey { it.id }
                ) { index ->
                    val item = wallpapers[index]

                    item?.let {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.src.portrait)
                                .crossfade(true)
                                .build(),
                            contentScale = ContentScale.Fit,
                            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(shape = ShapeDefaults.Medium)
                                .clickable {
                                    onImageClick(it.id)
                                }
                        )
                    }
                }
            }
        }
        is LoadState.Error -> {
            ErrorLayout(wallpapers = wallpapers)
        }
    }
}

@Composable
private fun ErrorLayout(
    wallpapers: LazyPagingItems<Photo>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            modifier = Modifier.padding(bottom = 10.dp),
        )
        Text(
            text = stringResource(id = R.string.retry),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
               wallpapers.refresh()
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewParameterProvider::class)
    photos: List<Photo>
) {
    val pagingData = PagingData.from(photos)
    val fakeDataFlow = MutableStateFlow(pagingData)
    val lazyPagingItems = fakeDataFlow.collectAsLazyPagingItems()

    AppTheme {
        HomeScreen(
            wallpapers = lazyPagingItems,
            networkState = NetworkState.AVAILABLE,
            onImageClick = {}
        )
    }
}