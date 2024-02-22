package news.database

import news.model.News
import org.example.kmpnews.data.news.NewsEntity

fun NewsEntity.toDomain(): News =
    News(
        sourceId = null,
        sourceName = sourceName,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )

fun List<NewsEntity>.toDomain(): List<News> =
    map { it.toDomain() }