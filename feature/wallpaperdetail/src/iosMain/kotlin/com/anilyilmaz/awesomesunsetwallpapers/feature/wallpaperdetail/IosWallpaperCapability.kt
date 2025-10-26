package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithURL
import platform.UIKit.UIImage
import platform.UIKit.UIImageWriteToSavedPhotosAlbum
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class IosWallpaperCapability : WallpaperCapability {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun performPrimaryAction(imageUrl: String) {
        val url = NSURL.URLWithString(imageUrl) ?: error("Bad URL")

        // Download bytes with NSURLSession (suspending)
        val data: NSData = suspendCancellableCoroutine { cont ->
            val task = NSURLSession.sharedSession.dataTaskWithURL(url) { d, _, e ->
                when {
                    e != null -> cont.resumeWithException(NSErrorException(e))
                    d == null  -> cont.resumeWithException(IllegalStateException("No data"))
                    else       -> cont.resume(d)
                }
            }
            cont.invokeOnCancellation { task.cancel() }
            task.resume()
        }

        val image = UIImage(data = data) ?: error("Invalid image")

        // UIKit call on main thread
        withContext(Dispatchers.Main) {
            UIImageWriteToSavedPhotosAlbum(image, null, null, null)
        }
    }
}

private class NSErrorException(val nsError: NSError) :
    RuntimeException(nsError.localizedDescription ?: "NSError")

actual fun defaultWallpaperCtaText(): String = "Download"