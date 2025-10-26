package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.IosWallpaperCapability
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperCapability
import org.koin.dsl.module

val platformWallpaperDetailModule = module {
    factory<WallpaperCapability> { IosWallpaperCapability() }
}