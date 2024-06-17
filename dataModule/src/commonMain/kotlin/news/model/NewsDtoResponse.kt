package news.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pagination.PagedData

@Serializable
data class NewsDtoResponse(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val articles: List<NewsDto>
)

fun NewsDtoResponse.toDomain(): List<News> = articles
    .map { it.toDomain() }
    .filter { it.url != "https://removed.com" } //todo

fun NewsDtoResponse.toPagedData(): PagedData<News> =
    PagedData(
        totalResults,
        articles
            .filter { it.url != "https://removed.com" } //todo
            .map { it.toDomain() }
    )