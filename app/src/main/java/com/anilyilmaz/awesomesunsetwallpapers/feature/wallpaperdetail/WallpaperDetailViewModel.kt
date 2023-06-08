package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import android.app.Application
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anilyilmaz.awesomesunsetwallpapers.BuildConfig
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetPhotoUseCase
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.SetTempFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class WallpaperDetailViewModel @Inject constructor(private val application: Application,
                                                   private val wallpaperManager: WallpaperManager,
                                                   private val getPhotoUseCase: GetPhotoUseCase,
                                                  private val setTempFileUseCase: SetTempFileUseCase
                                                   ): ViewModel() {

    private val _uiState = MutableStateFlow<WallpaperDetailUiState>(WallpaperDetailUiState.Loading)
    val uiState: StateFlow<WallpaperDetailUiState> = _uiState.asStateFlow()

    fun getWallpaper(id: Int) = viewModelScope.launch {
        _uiState.value = WallpaperDetailUiState.Loading

        try {
            val photo = getPhotoUseCase.getPhoto(id)
            _uiState.value = WallpaperDetailUiState.Success(photo.src.portrait, photo.photographer)
        } catch (e: Exception) {
            _uiState.value = WallpaperDetailUiState.Error
        }
    }

    suspend fun getTempImage(drawable: Drawable): File {
        val tempImage = setTempFileUseCase.setTempImage(drawable.toBitmap(),
            CompressFormat.JPEG, "wallpaper")

        return tempImage
    }

    fun getContentUri(file: File): Uri {
        return FileProvider.getUriForFile(application,
            BuildConfig.APPLICATION_ID + ".provider", file)
    }

    fun getCropAndSetWallpaperIntent(contentUri: Uri): Intent {
        return wallpaperManager.getCropAndSetWallpaperIntent(contentUri)
    }
}