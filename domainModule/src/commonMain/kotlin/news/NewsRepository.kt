package news

import kotlinx.coroutines.flow.Flow
import news.model.News
import news.model.NewsCategory

interface NewsRepository {
    suspend fun getNews(): List<News>
    suspend fun getTopHeadlinesNews(category: NewsCategory): List<News>
    suspend fun addNewsToFavourite(news: News)
    suspend fun deleteNewsFromFavourite(news: News)
    suspend fun flowFavoriteNews(): Flow<List<News>>
}