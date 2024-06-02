package news.topheadlines

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import news.model.News
import news.model.NewsCategory

data class NewsTopHeadlinesUiState(
    val selectedCategory: NewsCategory,
    val pagingData: Flow<PagingData<News>>
)