package com.anilyilmaz.awesomesunsetwallpapers.composeApp.di

import com.anilyilmaz.awesomesunsetwallpapers.core.common.di.dispatchersModule
import com.anilyilmaz.awesomesunsetwallpapers.core.data.di.dataModule
import com.anilyilmaz.awesomesunsetwallpapers.core.domain.di.domainModule
import com.anilyilmaz.awesomesunsetwallpapers.core.network.di.dataSourceModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.home.di.homeModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.main.di.mainModule
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.di.wallpaperDetailModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
    platformModules: List<Module> = emptyList(),
): KoinApplication = startKoin {
    appDeclaration()
    modules(
        dispatchersModule,
        dataSourceModule,
        dataModule,
        domainModule,
        mainModule,
        homeModule,
        wallpaperDetailModule,
        * platformModules.toTypedArray()
    )
}