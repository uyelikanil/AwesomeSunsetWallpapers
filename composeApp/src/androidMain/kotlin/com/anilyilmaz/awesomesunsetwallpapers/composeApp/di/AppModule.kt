package com.anilyilmaz.awesomesunsetwallpapers.composeApp.di

import android.net.ConnectivityManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ConnectivityManager> {
        androidContext().getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }
}