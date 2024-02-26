package news.favorite

import core.flow.AnyStateFlow
import news.model.News

interface NewsFavoriteComponent {
    val state: AnyStateFlow<NewsFavoriteUiState>

    fun onNewsSelected(news: News)
}