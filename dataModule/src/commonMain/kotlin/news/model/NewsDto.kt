package news.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    @SerialName("source") val source: Source,
    @SerialName("author") val author: String?,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("url") val url: String,
    @SerialName("urlToImage") val urlToImage: String?,
    @SerialName("publishedAt") val publishedAt: Instant,
    @SerialName("content") val content: String?,
) {
    @Serializable
    data class Source(
        @SerialName("id") val id: String?,
        @SerialName("name") val name: String,
    )
}

fun NewsDto.toDomain(): News =
    News(
        sourceId = source.id,
        sourceName = source.name,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )