package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

interface WallpaperCapability {
    suspend fun performPrimaryAction(imageUrl: String)
}

expect fun defaultWallpaperCtaText(): String