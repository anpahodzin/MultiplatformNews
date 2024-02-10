package news.list

import core.flow.AnyStateFlow
import news.model.News
import news.model.NewsCategory

interface NewsListComponent {
    val state: AnyStateFlow<NewsListUiState>

    fun onNewsSelected(news: News)

    fun onCategorySelected(category: NewsCategory)
}