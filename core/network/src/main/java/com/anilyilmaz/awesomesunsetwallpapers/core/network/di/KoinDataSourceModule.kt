package com.anilyilmaz.awesomesunsetwallpapers.core.network.di

import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSource
import com.anilyilmaz.awesomesunsetwallpapers.core.network.datasource.PexelsDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::PexelsDataSourceImpl) bind PexelsDataSource::class
}