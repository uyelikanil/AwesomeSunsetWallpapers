package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import android.app.WallpaperManager
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val wallpaperDetailModule = module {
    viewModelOf(::WallpaperDetailViewModel)

    single<WallpaperManager> {
        WallpaperManager.getInstance(androidContext())
    }
}