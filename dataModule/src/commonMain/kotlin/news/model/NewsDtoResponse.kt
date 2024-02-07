package news.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import news.News

@Serializable
data class NewsDtoResponse(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Long,
    @SerialName("articles") val articles: List<NewsDto>
)

fun NewsDtoResponse.toDomain(): List<News> =
    articles.map { it.toDomain() }