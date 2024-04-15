package news.topheadlines

import news.model.News
import news.model.NewsCategory

sealed class NewsTopHeadlinesUiState {

    data object Initial : NewsTopHeadlinesUiState()

    data class Loading(
        val selectedCategory: NewsCategory,
    ) : NewsTopHeadlinesUiState()

    data class Error(
        val selectedCategory: NewsCategory,
    ) : NewsTopHeadlinesUiState()

    data class Data(
        val selectedCategory: NewsCategory,
        val newsList: List<News>,
    ) : NewsTopHeadlinesUiState()
}

fun NewsTopHeadlinesUiState.getNewsOrNull(): List<News>? =
    when (this) {
        is NewsTopHeadlinesUiState.Data -> newsList
        is NewsTopHeadlinesUiState.Error -> null
        is NewsTopHeadlinesUiState.Initial -> null
        is NewsTopHeadlinesUiState.Loading -> null
    }

fun NewsTopHeadlinesUiState.getCategoryOrNull(): NewsCategory? =
    when (this) {
        is NewsTopHeadlinesUiState.Data -> selectedCategory
        is NewsTopHeadlinesUiState.Error -> selectedCategory
        is NewsTopHeadlinesUiState.Initial -> null
        is NewsTopHeadlinesUiState.Loading -> selectedCategory
    }