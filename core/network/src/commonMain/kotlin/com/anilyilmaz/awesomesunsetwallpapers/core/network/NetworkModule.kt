package com.anilyilmaz.awesomesunsetwallpapers.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect val httpClientEngine: HttpClientEngineFactory<out HttpClientEngineConfig>
expect val pexelsAuthKey: String

object NetworkModule {

    private const val DEFAULT_REQUEST_TIMEOUT_MS: Long = 30_000L
    private const val DEFAULT_CONNECT_TIMEOUT_MS: Long = 30_000L
    private const val DEFAULT_SOCKET_TIMEOUT_MS: Long = 30_000L

    fun createHttpClient(baseUrl: String): HttpClient =
        HttpClient(httpClientEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        coerceInputValues = true
                        explicitNulls = true
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = DEFAULT_REQUEST_TIMEOUT_MS
                connectTimeoutMillis = DEFAULT_CONNECT_TIMEOUT_MS
                socketTimeoutMillis = DEFAULT_SOCKET_TIMEOUT_MS
            }

            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.BODY
            }

            defaultRequest {
                url(baseUrl)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Authorization, pexelsAuthKey)
            }
        }
}