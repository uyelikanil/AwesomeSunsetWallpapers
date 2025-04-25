package com.anilyilmaz.awesomesunsetwallpapers.core.data.di

import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepository
import com.anilyilmaz.awesomesunsetwallpapers.core.data.repository.PhotoRepositoryImpl
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import org.koin.dsl.module

val dataModule = module {
    single<PhotoRepository> { PhotoRepositoryImpl(get<PexelsDataSource>(), get()) }
}