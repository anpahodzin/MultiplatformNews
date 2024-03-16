package news.topheadlines

import news.model.News
import news.model.NewsCategory

sealed class NewsListUiState {

    data object Initial : NewsListUiState()

    data class Loading(
        val selectedCategory: NewsCategory,
    ) : NewsListUiState()

    data class Error(
        val selectedCategory: NewsCategory,
    ) : NewsListUiState()

    data class Data(
        val selectedCategory: NewsCategory,
        val newsList: List<News>,
    ) : NewsListUiState()
}

fun NewsListUiState.getNewsOrNull(): List<News>? =
    when (this) {
        is NewsListUiState.Data -> newsList
        is NewsListUiState.Error -> null
        is NewsListUiState.Initial -> null
        is NewsListUiState.Loading -> null
    }

fun NewsListUiState.getCategoryOrNull(): NewsCategory? =
    when (this) {
        is NewsListUiState.Data -> selectedCategory
        is NewsListUiState.Error -> selectedCategory
        is NewsListUiState.Initial -> null
        is NewsListUiState.Loading -> selectedCategory
    }