package com.anilyilmaz.awesomesunsetwallpapers.core.local.di

import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.WallpaperDatabase
import com.anilyilmaz.awesomesunsetwallpapers.core.local.datasource.FavoriteWallpaperLocalDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.local.datasource.RoomFavoriteWallpaperDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localModule = module {
    single { get<WallpaperDatabase>().favoriteWallpaperDao() }
    singleOf(::RoomFavoriteWallpaperDataSource) bind FavoriteWallpaperLocalDataSource::class
}
