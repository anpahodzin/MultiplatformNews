package news

import kotlinx.coroutines.flow.Flow
import news.model.News
import news.model.NewsCategory

interface NewsRepository {
    suspend fun getNews(): List<News>
    suspend fun getTopHeadlinesNews(category: NewsCategory): List<News>
    //Favorite
    suspend fun addOrDeleteFavoriteNews(news: News): Boolean
    suspend fun isFavouriteNews(news: News): Boolean
    suspend fun flowFavoriteNews(): Flow<List<News>>
}