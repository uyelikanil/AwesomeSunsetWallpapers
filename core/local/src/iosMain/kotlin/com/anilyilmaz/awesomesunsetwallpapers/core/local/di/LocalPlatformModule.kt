package com.anilyilmaz.awesomesunsetwallpapers.core.local.di

import androidx.room.Room
import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.WallpaperDatabase
import com.anilyilmaz.awesomesunsetwallpapers.core.local.database.buildWallpaperDatabase
import org.koin.dsl.module
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

val localPlatformModule = module {
    single {
        buildWallpaperDatabase(
            Room.databaseBuilder<WallpaperDatabase>(
                name = "${documentDirectory()}/wallpapers.db",
            )
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val directoryUrl = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(directoryUrl?.path)
}
