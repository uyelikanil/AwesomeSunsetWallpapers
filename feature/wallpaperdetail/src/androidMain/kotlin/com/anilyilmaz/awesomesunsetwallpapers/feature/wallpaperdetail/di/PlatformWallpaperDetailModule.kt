package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import android.app.WallpaperManager
import android.content.Context
import coil3.ImageLoader
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.AndroidWallpaperCapability
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapability
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val platformWallpaperDetailModule = module {
    single { ImageLoader(androidContext()) }

    single<WallpaperManager> {
        WallpaperManager.getInstance(androidContext())
    }

    factory<WallpaperCapability> {
        AndroidWallpaperCapability(
            context = get<Context>(),
            wallpaperManager = get<WallpaperManager>(),
            imageLoader = get(),
            setTempFileUseCase = get()
        )
    }
}