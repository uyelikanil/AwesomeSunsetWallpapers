package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.testdoubles

import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapability
import com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform.WallpaperCapabilityResult
import kotlinx.coroutines.CompletableDeferred

class FakeWallpaperCapability : WallpaperCapability {
    val resultDeferred = CompletableDeferred<WallpaperCapabilityResult>()

    override suspend fun performPrimaryAction(imageUrl: String): WallpaperCapabilityResult {
        return resultDeferred.await()
    }

    fun completeSuccess() {
        resultDeferred.complete(WallpaperCapabilityResult.Success)
    }

    fun completeError(message: String) {
        resultDeferred.complete(WallpaperCapabilityResult.Error(message))
    }
}