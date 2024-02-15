package news

import news.api.NewsApi
import news.model.News
import news.model.NewsCategory
import news.model.toDomain

internal class NewsDataRepository(
    private val api: NewsApi,
    private val cache: NewsMemoryCache
) : NewsRepository {

    override suspend fun getNews(): List<News> {
        return cache.get() ?: api
            .getEverythingNews("tesla", 1, 20) //todo HARDCODED data
            .toDomain()
            .filter { it.url != "https://removed.com" }
            .also { cache.set(value = it) }
    }

    override suspend fun getTopHeadlinesNews(category: NewsCategory): List<News> =
        cache.get(key = category) ?: api
            .getTopHeadlinesNews(category = category.name)
            .toDomain()
            .filter { it.url != "https://removed.com" }
            .also { cache.set(key = category, value = it) }
}