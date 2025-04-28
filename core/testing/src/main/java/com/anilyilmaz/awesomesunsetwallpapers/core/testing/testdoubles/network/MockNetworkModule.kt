package com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.network

import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoExpandedTestData
import com.anilyilmaz.awesomesunsetwallpapers.core.testing.testdoubles.modelfactory.pexelsPhotoTestData
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json

object MockNetworkModule {
    fun createHttpClient(dispatcher: CoroutineDispatcher): HttpClient = HttpClient(MockEngine) {
        engine {
            this.dispatcher = dispatcher
            addHandler { request ->
                when {
                    request.url.encodedPath.startsWith("/v1/photos/") -> {
                        val pathId = request.url.encodedPath.trimStart('/').split('/').getOrNull(2)
                        val id = pathId?.toString()?.toLongOrNull()
                        id?.let {
                            val content = Json.encodeToString(pexelsPhotoTestData(it))
                            respond(
                                content = ByteReadChannel(content),
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        } ?: run {
                            respondError(HttpStatusCode.BadRequest)
                        }
                    }
                    request.url.encodedPath.startsWith("/v1/search") -> {
                        respond(
                            content = ByteReadChannel(Json.encodeToString(
                                pexelsPhotoExpandedTestData()
                            )),
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    else -> respondError(HttpStatusCode.NotFound)
                }
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }
    }
}