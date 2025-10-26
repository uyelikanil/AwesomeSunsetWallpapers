package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailViewModel
import org.koin.dsl.module

val wallpaperDetailModule = module {
    factory { (id: Long) ->
        WallpaperDetailViewModel(
            photoRepository = get<PhotoRepository>(),
            wallpaperId = id,
        )
    }
}