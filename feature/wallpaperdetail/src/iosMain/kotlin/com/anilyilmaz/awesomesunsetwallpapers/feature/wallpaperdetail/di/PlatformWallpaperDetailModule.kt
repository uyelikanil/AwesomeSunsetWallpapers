package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.IosWallpaperCapability
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapability
import org.koin.dsl.module

val platformWallpaperDetailModule = module {
    factory<WallpaperCapability> { IosWallpaperCapability() }
}