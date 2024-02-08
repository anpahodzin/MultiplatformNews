package news.detailed

import news.News

sealed class NewsDetailedUiState {
    data object Loading : NewsDetailedUiState()

    data object Error : NewsDetailedUiState()

    data class Data(val newsList: List<News>) : NewsDetailedUiState()
}