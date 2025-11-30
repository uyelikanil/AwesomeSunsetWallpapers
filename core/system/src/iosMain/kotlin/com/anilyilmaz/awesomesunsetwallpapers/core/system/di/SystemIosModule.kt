package com.anilyilmaz.awesomesunsetwallpapers.core.system.di

import com.anilyilmaz.awesomesunsetwallpapers.core.system.IosNetworkMonitor
import com.anilyilmaz.awesomesunsetwallpapers.core.system.NetworkMonitor
import org.koin.dsl.module

val systemIosModule = module {
    single<NetworkMonitor> { IosNetworkMonitor }
}