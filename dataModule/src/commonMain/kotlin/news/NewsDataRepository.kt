package news

import app.cash.sqldelight.async.coroutines.awaitAsOne
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import news.api.NewsApi
import news.database.NewsDatabase
import news.database.toDomain
import news.model.News
import news.model.NewsCategory
import news.model.toDomain

internal class NewsDataRepository(
    private val api: NewsApi,
    private val cache: NewsMemoryCache,
    private val database: NewsDatabase,
) : NewsRepository {

    override suspend fun getEverythingNews(query: String, page: Int, pageSize: Int): List<News> {
        val cacheKey = query + page + pageSize
        return cache[cacheKey] ?: api
            .getEverythingNews(query, page, pageSize)
            .toDomain()
            .also { cache[cacheKey] = it }
    }

    override suspend fun getTopHeadlinesNews(category: NewsCategory): List<News> =
        cache[category] ?: api
            .getTopHeadlinesNews(category = category.name)
            .toDomain()
            .also { cache[category] = it }

    private suspend fun addNewsToFavourite(news: News) = with(news) {
        database().insertFavorite(
            sourceName = sourceName,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content
        )
    }

    private suspend fun deleteNewsFromFavourite(news: News) = with(news) {
        database().deleteFavorite(url = url)
    }

    override suspend fun addOrDeleteFavoriteNews(news: News): Boolean {
        return database().transactionWithResult {
            if (isFavouriteNews(news)) {
                deleteNewsFromFavourite(news)
                false
            } else {
                addNewsToFavourite(news)
                true
            }
        }
    }

    override suspend fun isFavouriteNews(news: News): Boolean =
        database().isExist(news.url).awaitAsOne()

    override suspend fun flowFavoriteNews(): Flow<List<News>> =
        database().getAllFavorite()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.toDomain() }
}