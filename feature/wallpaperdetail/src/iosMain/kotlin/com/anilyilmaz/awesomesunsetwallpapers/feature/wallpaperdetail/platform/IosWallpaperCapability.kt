package com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail.platform

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithURL
import platform.Photos.*

/**
 * iOS implementation of [WallpaperCapability].
 *
 * Flow:
 * 1. Check / request Photos *add-only* permission (write-only via [PHAccessLevelAddOnly]).
 * 2. Download the wallpaper image from a String URL called [imageUrl].
 * 3. Save the image data into the user's Photos library.
 * 4. Return:
 *    - [WallpaperCapabilityResult.Success] with a user-facing message on success.
 *    - [WallpaperCapabilityResult.Error] with the error message on failure.
 */
class IosWallpaperCapability : WallpaperCapability {

    override suspend fun performPrimaryAction(
        imageUrl: String,
    ): WallpaperCapabilityResult = withContext(Dispatchers.Default) {
        try {
            ensurePhotoLibraryAccess()

            val data = downloadImageData(imageUrl)

            saveImageToPhotos(data)

            WallpaperCapabilityResult.Success
        } catch (t: Throwable) {
            WallpaperCapabilityResult.Error(t.message)
        }
    }

    private suspend fun ensurePhotoLibraryAccess() {
        val current = PHPhotoLibrary.authorizationStatusForAccessLevel(PHAccessLevelAddOnly)

        if (current == PHAuthorizationStatusAuthorized) return

        val result = requestPhotoAuthorization()

        if (result != PHAuthorizationStatusAuthorized) {
            throw IllegalStateException(
                "Access to Photos is not granted. Please enable photo access in Settings."
            )
        }
    }

    private suspend fun requestPhotoAuthorization(): PHAuthorizationStatus =
        suspendCancellableCoroutine { cont ->
            PHPhotoLibrary.requestAuthorizationForAccessLevel(
                accessLevel = PHAccessLevelAddOnly
            ) { status ->
                cont.resume(status)
            }
        }

    private suspend fun downloadImageData(urlString: String): NSData =
        suspendCancellableCoroutine { cont ->
            val url = NSURL.URLWithString(urlString)
            if (url == null) {
                cont.resumeWithException(
                    IllegalArgumentException("Invalid URL: $urlString")
                )
                return@suspendCancellableCoroutine
            }

            val task = NSURLSession.sharedSession.dataTaskWithURL(url) { data, _, error ->
                when {
                    error != null -> {
                        cont.resumeWithException(error.toThrowable())
                    }

                    data != null -> {
                        cont.resume(data)
                    }

                    else -> {
                        cont.resumeWithException(
                            IllegalStateException("No data and no error while downloading image")
                        )
                    }
                }
            }

            cont.invokeOnCancellation { task.cancel() }

            task.resume()
        }

    private suspend fun saveImageToPhotos(data: NSData) {
        suspendCancellableCoroutine { cont ->
            val photoLibrary = PHPhotoLibrary.sharedPhotoLibrary()

            photoLibrary.performChanges(
                changeBlock = {
                    val request = PHAssetCreationRequest.creationRequestForAsset()
                    val options = PHAssetResourceCreationOptions()

                    request.addResourceWithType(
                        type = PHAssetResourceTypePhoto,
                        data = data,
                        options = options
                    )
                },
                completionHandler = { success, error ->
                    when {
                        success -> cont.resume(Unit)
                        error != null -> cont.resumeWithException(error.toThrowable())
                        else -> cont.resumeWithException(
                            IllegalStateException("Unknown error while saving to Photos")
                        )
                    }
                }
            )
        }
    }

    private fun NSError.toThrowable(): Throwable {
        val message = localizedDescription ?: "iOS error"
        return IllegalStateException(message)
    }
}