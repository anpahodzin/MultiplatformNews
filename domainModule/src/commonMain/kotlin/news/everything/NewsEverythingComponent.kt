package news.everything

import core.flow.AnyStateFlow
import news.model.News

interface NewsEverythingComponent {
    val state: AnyStateFlow<NewsEverythingUiState>

    fun onNewsSelected(news: News)

    fun onSearchQueryChanged(query: String)

    fun refresh()
}