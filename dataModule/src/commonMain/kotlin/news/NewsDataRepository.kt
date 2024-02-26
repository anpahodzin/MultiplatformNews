package news

import app.cash.sqldelight.async.coroutines.awaitAsList
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

    override suspend fun getNews(): List<News> {
        return cache.get() ?: api
            .getEverythingNews("tesla", 1, 20) //todo HARDCODED data
            .toDomain()
            .also { cache.set(value = it) }
    }

    override suspend fun getTopHeadlinesNews(category: NewsCategory): List<News> =
        cache.get(key = category) ?: api
            .getTopHeadlinesNews(category = category.name)
            .toDomain()
            .also { cache.set(key = category, value = it) }

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
        database().isExist(news.url).executeAsOne()

    override suspend fun flowFavoriteNews(): Flow<List<News>> =
        database().getAllFavorite()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { it.toDomain() }
}