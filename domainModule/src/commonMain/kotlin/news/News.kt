package news

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val sourceId: String?,
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Instant,
    val content: String,
)