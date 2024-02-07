package news

import news.api.NewsApi
import news.model.toDomain

internal class NewsDataRepository(
    private val api: NewsApi,
    private val cache: NewsMemoryCache
) : NewsRepository {

    override suspend fun getNews(): List<News> {
        return cache.get() ?: api
            .getEverythingNews("tesla", 1, 20) //todo HARDCODED data
            .toDomain()
            .also { cache.set(value = it) }
    }
}