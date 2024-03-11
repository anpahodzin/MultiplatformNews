package news.everything

import news.model.News
import news.model.NewsCategory

sealed class NewsEverythingUiState {

    data object Initial : NewsEverythingUiState()

    data class Loading(
        val selectedCategory: NewsCategory,
    ) : NewsEverythingUiState()

    data class Error(
        val selectedCategory: NewsCategory,
    ) : NewsEverythingUiState()

    data class Data(
        val selectedCategory: NewsCategory,
        val newsList: List<News>,
    ) : NewsEverythingUiState()
}

fun NewsEverythingUiState.getNewsOrNull(): List<News>? =
    when (this) {
        is NewsEverythingUiState.Data -> newsList
        is NewsEverythingUiState.Error -> null
        is NewsEverythingUiState.Initial -> null
        is NewsEverythingUiState.Loading -> null
    }

fun NewsEverythingUiState.getCategoryOrNull(): NewsCategory? =
    when (this) {
        is NewsEverythingUiState.Data -> selectedCategory
        is NewsEverythingUiState.Error -> selectedCategory
        is NewsEverythingUiState.Initial -> null
        is NewsEverythingUiState.Loading -> selectedCategory
    }