package news.everything

import news.model.News

sealed class NewsEverythingUiState {
    abstract val search: String

    data class Empty(
        override val search: String,
    ) : NewsEverythingUiState()

    data class Loading(
        override val search: String,
    ) : NewsEverythingUiState()

    data class Error(
        override val search: String,
    ) : NewsEverythingUiState()

    data class Data(
        override val search: String,
        val newsList: List<News>,
    ) : NewsEverythingUiState()
}

fun NewsEverythingUiState.getNewsOrNull(): List<News>? =
    when (this) {
        is NewsEverythingUiState.Data -> newsList
        is NewsEverythingUiState.Error -> null
        is NewsEverythingUiState.Empty -> emptyList()
        is NewsEverythingUiState.Loading -> null
    }