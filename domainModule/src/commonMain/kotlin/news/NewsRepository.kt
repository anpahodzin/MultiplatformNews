package news

import news.model.News
import news.model.NewsCategory

interface NewsRepository {
    suspend fun getNews(): List<News>
    suspend fun getTopHeadlinesNews(category: NewsCategory): List<News>
}