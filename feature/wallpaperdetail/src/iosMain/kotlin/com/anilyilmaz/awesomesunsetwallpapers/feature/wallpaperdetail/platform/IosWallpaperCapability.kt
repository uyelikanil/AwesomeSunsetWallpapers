package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

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
        try {
            val url = NSURL.URLWithString(imageUrl)
            if (url == null) {
                println("IosWallpaperCapability: invalid URL: $imageUrl")
                return
            }

            val data: NSData = suspendCancellableCoroutine { cont ->
                val task = NSURLSession.sharedSession.dataTaskWithURL(url) { d, _, e ->
                    when {
                        e != null -> {
                            println(
                                "IosWallpaperCapability: network error: " +
                                        (e.localizedDescription ?: "unknown")
                            )
                            cont.resumeWithException(NSErrorException(e))
                        }
                        d == null -> {
                            println("IosWallpaperCapability: no data from $imageUrl")
                            cont.resumeWithException(
                                IllegalStateException("No data from $imageUrl")
                            )
                        }
                        else -> cont.resume(d)
                    }
                }
                cont.invokeOnCancellation { task.cancel() }
                task.resume()
            }

            withContext(Dispatchers.Main) {
                val image: UIImage? = UIImage(data = data)
                if (image == null) {
                    println("IosWallpaperCapability: failed to decode image from $imageUrl")
                    return@withContext
                }

                UIImageWriteToSavedPhotosAlbum(image, null, null, null)
                println("IosWallpaperCapability: image saved to Photos")
            }
        } catch (t: Throwable) {
            println("IosWallpaperCapability: performPrimaryAction failed: ${t.message}")
            t.printStackTrace()
        }
    }
}

private class NSErrorException(val nsError: NSError) :
    RuntimeException(nsError.localizedDescription ?: "NSError")

actual fun defaultWallpaperCtaText(): String = "Download"