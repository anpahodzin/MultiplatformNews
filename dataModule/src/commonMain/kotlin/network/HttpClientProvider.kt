package network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider(
    private val json: Json,
) {
    fun get(endpoint: String, defaultParameters: Map<String, String> = emptyMap()) =
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.v(message, null, "http")
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    url.takeFrom(endpoint)
                    defaultParameters.forEach {
                        parameters.append(it.key, it.value)
                    }
                }
            }
        }

    fun get(endpoint: String, config: HttpClientConfig<*>.() -> Unit = {}) =
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.v(message, null, "http")
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    url.takeFrom(endpoint)
                }
            }
        }
}