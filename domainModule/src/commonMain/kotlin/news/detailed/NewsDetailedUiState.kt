package news.detailed

import news.model.News

sealed class NewsDetailedUiState {
    data object Loading : NewsDetailedUiState()

    data object Error : NewsDetailedUiState()

    data class Data(
        val news: News,
        val isFavoriteNews: Boolean
    ) : NewsDetailedUiState()
}