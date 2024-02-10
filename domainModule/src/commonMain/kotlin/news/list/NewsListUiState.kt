package news.list

import news.model.News
import news.model.NewsCategory

sealed class NewsListUiState {
    data object Loading : NewsListUiState()

    data object Error : NewsListUiState()

    data class Data(
        val newsList: List<News>,
        val selectedCategory: NewsCategory
    ) : NewsListUiState()
}