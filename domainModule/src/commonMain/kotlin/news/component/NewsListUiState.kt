package news.component

import news.News

sealed class NewsListUiState {
    data object Loading : NewsListUiState()

    data object Error : NewsListUiState()

    data class Data(val newsList: List<News>) : NewsListUiState()
}