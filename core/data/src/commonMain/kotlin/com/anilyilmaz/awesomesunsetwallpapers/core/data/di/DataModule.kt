package com.anilyilmaz.awesomesunsetwallpapers.core.data.di

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.FavoriteWallpaperRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoListRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.FavoriteWallpaperRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.repository.PhotoListRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import org.koin.dsl.module

val dataModule = module {
    single<FavoriteWallpaperRepository> { FavoriteWallpaperRepositoryImpl(get()) }
    single<PhotoRepository> { PhotoRepositoryImpl(get<PexelsDataSource>(), get()) }
    single<PhotoListRepository> { PhotoListRepositoryImpl(get<PexelsDataSource>()) }
}

object SharedModules { fun common() = listOf(dataModule) }
