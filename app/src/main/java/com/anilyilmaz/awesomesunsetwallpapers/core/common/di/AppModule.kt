package com.anilyilmaz.awesomesunsetwallpapers.core.common.di

import android.app.Application
import android.app.WallpaperManager
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(application: Application): ConnectivityManager =
        application.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    @Provides
    @Singleton
    fun provideWallpaperManager(application: Application): WallpaperManager =
        WallpaperManager.getInstance(application)

}