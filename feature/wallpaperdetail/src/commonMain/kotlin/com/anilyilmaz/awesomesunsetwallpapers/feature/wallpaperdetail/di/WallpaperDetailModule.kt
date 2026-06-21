package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di

import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.usecase.GetWallpaperUseCase
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.WallpaperDetailViewModel
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapability
import org.koin.dsl.module

val wallpaperDetailModule = module {
    factory { (id: Long) ->
        WallpaperDetailViewModel(
            getWallpaperUseCase = get<GetWallpaperUseCase>(),
            favoriteWallpaperRepository = get<FavoriteWallpaperRepository>(),
            wallpaperId = id,
            capability = get<WallpaperCapability>()
        )
    }
}
