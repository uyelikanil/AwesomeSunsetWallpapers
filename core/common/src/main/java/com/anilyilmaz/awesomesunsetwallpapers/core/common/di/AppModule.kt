package com.anilyilmaz.awesomesunsetwallpapers.core.common.di

import android.app.WallpaperManager
import android.net.ConnectivityManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ConnectivityManager> {
        androidContext().getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }
    single<WallpaperManager> {
        WallpaperManager.getInstance(androidContext())
    }
}