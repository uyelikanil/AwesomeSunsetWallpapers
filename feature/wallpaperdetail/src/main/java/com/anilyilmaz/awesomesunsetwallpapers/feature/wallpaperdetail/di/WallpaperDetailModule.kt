package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val wallpaperDetailModule = module {
    viewModelOf(::WallpaperDetailViewModel)
}