package com.anilyilmaz.awesomesunsetwallpapers.core.system.di

import android.net.ConnectivityManager
import com.anilyilmaz.awesomesunsetwallpapers.core.system.AndroidNetworkMonitor
import com.anilyilmaz.awesomesunsetwallpapers.core.system.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val systemAndroidModule = module {
    single<NetworkMonitor> {
        AndroidNetworkMonitor(
            androidContext().getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        )
    }
}