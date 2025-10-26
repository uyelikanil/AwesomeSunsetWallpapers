package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

interface WallpaperCapability {
    suspend fun performPrimaryAction(imageUrl: String)
}

expect fun defaultWallpaperCtaText(): String