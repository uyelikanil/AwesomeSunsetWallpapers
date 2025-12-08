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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.anilyilmaz.awesomesunsetwallpapers.core.common.platform.Platform
import com.anilyilmaz.awesomesunsetwallpapers.core.common.platform.platform
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.TransparentCenterAlignedTopAppBar
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.WhiteTextOutlinedButton
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.md_theme_dark_primary
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.Res
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.download
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.error_occurred_while_setting_wallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.retry
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.set_as_a_wallpaper
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.something_went_wrong
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.something_went_wrong_try_again
import com.anilyilmaz.awesomesunsetwallpapers.core.resource.successfully_saved_to_your_gallery
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.BackHandlerCompat
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WallpaperDetailRoute(
    wallpaperId: Long,
    onNavigationClick: () -> Unit,
    onShowMessage: (String) -> Unit,
    viewModel: WallpaperDetailViewModel = koinViewModel(
        parameters = { parametersOf(wallpaperId) }
    ),
) {
    val platformContext = LocalPlatformContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val capabilityState by viewModel.capabilityState.collectAsStateWithLifecycle()

    WallpaperDetailScreen(
        context = platformContext,
        uiState = uiState,
        capabilityState = capabilityState,
        onNavigationClick = onNavigationClick,
        onRetry = viewModel::getWallpaper,
        onPrimaryAction = viewModel::onWallpaperAction,
        onPrimaryActionHandled = viewModel::onWallpaperActionHandled,
        onShowMessage = onShowMessage
    )
}

@Composable
internal fun WallpaperDetailScreen(
    context: PlatformContext,
    uiState: WallpaperDetailUiState,
    capabilityState: WallpaperDetailCapabilityState,
    onNavigationClick: () -> Unit,
    onRetry: () -> Unit,
    onPrimaryAction: () -> Unit,
    onPrimaryActionHandled: () -> Unit,
    onShowMessage: (String) -> Unit
) {
    var isImageFullScreen by remember { mutableStateOf(false) }

    BackHandlerCompat(enabled = isImageFullScreen) {
        isImageFullScreen = false
    }

    when (uiState) {
        WallpaperDetailUiState.Loading -> {
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
                CapabilityButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.BottomCenter)
                        .padding(16.dp)
                        .safeDrawingPadding(),
                    capabilityState = capabilityState,
                    ctaText = if(platform() == Platform.Android) {
                        stringResource(Res.string.set_as_a_wallpaper)
                    } else if(platform() == Platform.Ios) {
                        stringResource(Res.string.download)
                    } else {
                        null
                    },
                    onClick = onPrimaryAction
                )
            }
        }

        WallpaperDetailUiState.Error -> {
            ErrorLayout(onRetry = onRetry)
        }
    }

    val photographer = (uiState as? WallpaperDetailUiState.Success)?.photographer
    if (!isImageFullScreen) {
        TopAppBar(
            topAppBarText = photographer.orEmpty(),
            onNavigationClick = onNavigationClick
        )
    }

    CapabilityStateMessage(
        capabilityState = capabilityState,
        onShowMessage = onShowMessage,
        onPrimaryActionHandled = onPrimaryActionHandled
    )
}

@Composable
private fun TopAppBar(
    topAppBarText: String,
    onNavigationClick: () -> Unit,
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
    onRetry: () -> Unit,
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

@Composable
private fun CapabilityButton(
    modifier: Modifier,
    capabilityState: WallpaperDetailCapabilityState,
    ctaText: String?,
    onClick: () -> Unit,
) {
    val isLoading = capabilityState == WallpaperDetailUiState.Loading
    WhiteTextOutlinedButton(
        modifier = modifier,
        isLoading = isLoading,
        text = ctaText.orEmpty(),
        onClick = onClick
    )
}

@Composable
private fun CapabilityStateMessage(
    capabilityState: WallpaperDetailCapabilityState,
    onShowMessage: (String) -> Unit,
    onPrimaryActionHandled: () -> Unit,
) {
    val iosSuccessMessage = stringResource(Res.string.successfully_saved_to_your_gallery)
    val iosErrorMessage = stringResource(Res.string.something_went_wrong_try_again)
    val androidErrorMessage = stringResource(Res.string.error_occurred_while_setting_wallpaper)

    LaunchedEffect(capabilityState) {
        if (capabilityState == WallpaperDetailCapabilityState.Success) {
            if (platform() == Platform.Ios) {
                onShowMessage(iosSuccessMessage)
            }
            onPrimaryActionHandled()
        } else if (capabilityState is WallpaperDetailCapabilityState.Error) {
            if(platform() == Platform.Android) {
                onShowMessage(androidErrorMessage)
            } else if (platform() == Platform.Ios) {
                onShowMessage(iosErrorMessage)
            }
            onPrimaryActionHandled()
        }
    }
}