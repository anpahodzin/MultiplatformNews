package news

import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import news.model.News
import news.model.NewsCategory

interface NewsPagingRepository {
    suspend fun getPagingTopHeadlinesNews(
        config: PagingConfig,
        category: NewsCategory,
    ): Flow<PagingData<News>>
}