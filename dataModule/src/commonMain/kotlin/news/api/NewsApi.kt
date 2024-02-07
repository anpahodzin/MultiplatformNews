package news.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import news.model.NewsDtoResponse

internal class NewsApi(private val client: HttpClient) {

    suspend fun getEverythingNews(query: String?, page: Int, pageSize: Int): NewsDtoResponse =
        client.get {
            url { path("everything") }
            parameter("q", query)
//            parameter("pageSize", pageSize)
//            parameter("page", page)
        }.body<NewsDtoResponse>()
}