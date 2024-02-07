package news

interface NewsRepository {
    suspend fun getNews(): List<News>
}