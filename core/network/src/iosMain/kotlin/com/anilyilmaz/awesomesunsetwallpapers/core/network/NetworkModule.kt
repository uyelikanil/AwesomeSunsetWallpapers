package com.anilyilmaz.awesomesunsetwallpapers.core.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSBundle

actual val httpClientEngine: HttpClientEngineFactory<out HttpClientEngineConfig> = Darwin

actual val pexelsAuthKey: String
    get() = NSBundle.mainBundle
        .objectForInfoDictionaryKey("PEXELS_AUTH_KEY") as? String
        ?: ""
