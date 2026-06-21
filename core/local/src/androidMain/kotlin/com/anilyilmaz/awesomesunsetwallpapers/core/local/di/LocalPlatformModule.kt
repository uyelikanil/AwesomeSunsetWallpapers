package com.anilyilmaz.awesomesunsetwallpapers.core.local.di

import androidx.room.Room
import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.WallpaperDatabase
import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.buildWallpaperDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localPlatformModule = module {
    single {
        val context = androidContext().applicationContext
        val databaseFile = context.getDatabasePath("wallpapers.db")
        buildWallpaperDatabase(
            Room.databaseBuilder<WallpaperDatabase>(
                context = context,
                name = databaseFile.absolutePath,
            )
        )
    }
}
