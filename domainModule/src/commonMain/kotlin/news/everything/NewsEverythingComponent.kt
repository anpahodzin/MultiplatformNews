package news.everything

import core.flow.AnyStateFlow
import news.model.News
import news.model.NewsCategory

interface NewsEverythingComponent {
    val state: AnyStateFlow<NewsEverythingUiState>

    fun onNewsSelected(news: News)

    fun onCategorySelected(category: NewsCategory)

    fun refresh()
}