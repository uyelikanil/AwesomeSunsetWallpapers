package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.TransparentCenterAlignedTopAppBar
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.WhiteTextOutlinedButton
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.md_theme_dark_primary
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.retry
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.something_went_wrong
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun WallpaperDetailRoute(
    wallpaperId: Long,
    onNavigationClick: () -> Unit
) {
    val platformContext = LocalPlatformContext.current

    val koin = getKoin()
    val viewModel = remember(wallpaperId) {
        koin.get<WallpaperDetailViewModel> { parametersOf(wallpaperId) }
    }
    val capability = remember { koin.get<WallpaperCapability>() }
    val ctaText = remember { defaultWallpaperCtaText() }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle(WallpaperDetailUiState.Loading)

    WallpaperDetailScreen(
        context = platformContext,
        uiState = uiState,
        ctaText = ctaText,
        onPrimaryAction = { imageUrl -> capability.performPrimaryAction(imageUrl) },
        onRetry = viewModel::getWallpaper,
        onNavigationClick = onNavigationClick
    )
}

@Composable
internal fun WallpaperDetailScreen(
    context: PlatformContext,
    uiState: WallpaperDetailUiState,
    ctaText: String,
    onPrimaryAction: suspend (imageUrl: String) -> Unit,
    onRetry: () -> Unit,
    onNavigationClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var isImageFullScreen by remember { mutableStateOf(false) }
    var busy by remember { mutableStateOf(false) }

    BackHandlerCompat(enabled = isImageFullScreen) {
        isImageFullScreen = false
    }

    when (uiState) {
        WallpaperDetailUiState.Loading -> {
            TopAppBar(
                topAppBarText = "",
                onNavigationClick = onNavigationClick
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        is WallpaperDetailUiState.Success -> {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(uiState.wallpaperSrc)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { isImageFullScreen = !isImageFullScreen }
            )

            if (!isImageFullScreen) {
                TopAppBar(
                    topAppBarText = uiState.photographer,
                    onNavigationClick = onNavigationClick
                )

                WhiteTextOutlinedButton(
                    text = if (busy) "…" else ctaText,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.BottomCenter)
                        .padding(16.dp)
                        .safeDrawingPadding(),
                    onClick = {
                        if (!busy) {
                            scope.launch {
                                busy = true
                                runCatching { onPrimaryAction(uiState.wallpaperSrc) }
                                busy = false
                            }
                        }
                    }
                )
            }
        }

        WallpaperDetailUiState.Error -> {
            TopAppBar(
                topAppBarText = "",
                onNavigationClick = onNavigationClick
            )
            ErrorLayout(onRetry = onRetry)
        }
    }
}

@Composable
private fun TopAppBar(
    topAppBarText: String,
    onNavigationClick: () -> Unit
) {
    TransparentCenterAlignedTopAppBar(
        title = topAppBarText,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart),
        onNavigationClick = onNavigationClick
    )
}

@Composable
private fun ErrorLayout(
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(Res.string.something_went_wrong),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(Res.string.retry),
            modifier = Modifier.clickable { onRetry() },
            color = md_theme_dark_primary
        )
    }
}