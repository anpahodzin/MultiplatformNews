package network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider(
    private val json: Json,
) {

    fun get(block: DefaultRequest.DefaultRequestBuilder.() -> Unit = {}) =
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
//                Needed to bypass the CORS policy in JS
//                header(HttpHeaders.ContentType, ContentType.Application.Json)
                block()
            }
        }
}

fun DefaultRequest.DefaultRequestBuilder.rootUrl(endpoint: String) {
    url.takeFrom(endpoint)
}