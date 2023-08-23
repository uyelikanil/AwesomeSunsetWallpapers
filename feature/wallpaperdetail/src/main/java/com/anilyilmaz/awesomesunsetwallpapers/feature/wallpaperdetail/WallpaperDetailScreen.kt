package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.TransparentCenterAlignedTopAppBar
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.component.WhiteTextOutlinedButton
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.AppTheme
import com.anilyilmaz.awesomesunsetwallpapers.core.designsystem.theme.md_theme_dark_primary
import kotlinx.coroutines.launch
import java.io.File

private lateinit var imageDrawable: Drawable

@Composable
fun WallpaperDetailRoute(
    viewModel: WallpaperDetailViewModel = hiltViewModel(),
    getCropAndSetWallpaperIntent: (Uri) -> Intent,
    setTempImage: suspend (Bitmap, Bitmap.CompressFormat, String) -> File,
    onNavigationClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WallpaperDetailScreen(
        uiState = uiState,
        getCropAndSetWallpaperIntent = getCropAndSetWallpaperIntent,
        setTempImage = setTempImage,
        getWallpaper = viewModel::getWallpaper,
        onNavigationClick = onNavigationClick)
}

@Composable
internal fun WallpaperDetailScreen(
    uiState: WallpaperDetailUiState,
    getCropAndSetWallpaperIntent: (Uri) -> Intent,
    setTempImage: suspend (Bitmap, Bitmap.CompressFormat, String) -> File,
    getWallpaper: () -> Unit,
    onNavigationClick: () -> Unit
) {
    val context = LocalContext.current
    val composableScope = rememberCoroutineScope()
    var isImageFullScreen by remember { mutableStateOf(false) }

    BackHandler(enabled = isImageFullScreen) {
        if (isImageFullScreen) {
            isImageFullScreen = !isImageFullScreen
        }
    }

    Surface(color = Color.Black) {
        when(uiState) {
            is WallpaperDetailUiState.Loading -> {
                TopAppBar(
                    topAppBarText = "",
                    onNavigationClick = onNavigationClick
                )

                CircularProgressIndicator(modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                )
            }
            is WallpaperDetailUiState.Success ->  {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(uiState.wallpaperSrc)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    onSuccess = { success ->
                        imageDrawable = success.result.drawable
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            isImageFullScreen = !isImageFullScreen
                        }
                )

                if(!isImageFullScreen) {
                    TopAppBar(
                        topAppBarText =  uiState.photographer,
                        onNavigationClick = onNavigationClick
                    )

                    WhiteTextOutlinedButton(
                        text = stringResource(id = R.string.set_as_a_wallpaper),
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.BottomCenter)
                            .padding(16.dp)
                            .safeDrawingPadding(),
                        onClick = {
                            composableScope.launch {
                                setWallpaper(
                                    context = context,
                                    getCropAndSetWallpaperIntent = getCropAndSetWallpaperIntent,
                                    setTempImage = setTempImage
                                )
                            }
                        }
                    )
                }
            }
            is WallpaperDetailUiState.Error -> {
                TopAppBar(
                    topAppBarText = "",
                    onNavigationClick = onNavigationClick
                )
                ErrorLayout(getWallpaper = getWallpaper)
            }
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
    getWallpaper: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            modifier = Modifier.padding(bottom = 10.dp),
            color = White
        )
        Text(
            text = stringResource(id = R.string.retry),
            modifier = Modifier.clickable {
                getWallpaper()
            },
            color = md_theme_dark_primary
        )
    }
}

private suspend fun setWallpaper(
    context: Context,
    getCropAndSetWallpaperIntent: (Uri) -> Intent,
    setTempImage: suspend (Bitmap, Bitmap.CompressFormat, String) -> File
) {
    if(::imageDrawable.isInitialized) {
        try {
            val tempImage = setTempImage(imageDrawable.toBitmap(),
                Bitmap.CompressFormat.JPEG, "wallpaper")
            val contentUri = FileProvider.getUriForFile(context,
                context.packageName + ".provider", tempImage)
            val cropAndSetWallpaperIntent = getCropAndSetWallpaperIntent(contentUri)
            context.startActivity(cropAndSetWallpaperIntent)
        } catch (e: Exception) {
            Toast.makeText(context,
                context.getText(R.string.error_occurred_while_setting_wallpaper),
                Toast.LENGTH_SHORT).show()

            e.printStackTrace()
        }
    }
}

@Preview
@Composable
fun WallpaperDetailScreenPreview() {
    AppTheme {
        WallpaperDetailScreen(
            uiState = WallpaperDetailUiState.Success(
                wallpaperSrc = "https://images.pexels.com/photos/848573/pexels-photo-848573.jpeg?" +
                        "auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                photographer = "ANIL"),
            getCropAndSetWallpaperIntent = { Intent() },
            setTempImage = {_, _, _ -> File("")},
            getWallpaper = {},
            onNavigationClick = {}
        )
    }
}