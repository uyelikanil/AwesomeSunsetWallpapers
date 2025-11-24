package com.anilyilmaz.awesomesunsetwallpapers.core.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.okhttp.OkHttp

actual val httpClientEngine: HttpClientEngineFactory<out HttpClientEngineConfig> = OkHttp

actual val pexelsAuthKey: String = BuildConfig.PEXELS_AUTH_KEY
