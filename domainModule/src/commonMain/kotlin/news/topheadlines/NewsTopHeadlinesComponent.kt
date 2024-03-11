package news.topheadlines

import core.flow.AnyStateFlow
import news.model.News
import news.model.NewsCategory

interface NewsTopHeadlinesComponent {
    val state: AnyStateFlow<NewsListUiState>

    fun onNewsSelected(news: News)

    fun onCategorySelected(category: NewsCategory)

    fun refresh()
}