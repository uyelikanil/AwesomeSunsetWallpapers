package com.anilyilmaz.awesomesunsetwallpapers.core.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [FavoriteWallpaperEntity::class],
    version = 1,
    exportSchema = false,
)
@ConstructedBy(WallpaperDatabaseConstructor::class)
internal abstract class WallpaperDatabase : RoomDatabase() {
    abstract fun favoriteWallpaperDao(): FavoriteWallpaperDao
}

@Suppress("KotlinNoActualForExpect")
internal expect object WallpaperDatabaseConstructor : RoomDatabaseConstructor<WallpaperDatabase> {
    override fun initialize(): WallpaperDatabase
}

internal fun buildWallpaperDatabase(
    builder: RoomDatabase.Builder<WallpaperDatabase>,
): WallpaperDatabase = builder
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
