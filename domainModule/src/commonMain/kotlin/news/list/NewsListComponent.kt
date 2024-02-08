package news.list

import core.flow.AnyStateFlow
import news.News

interface NewsListComponent {
    val state: AnyStateFlow<NewsListUiState>

    fun onNewsSelected(news: News)
}