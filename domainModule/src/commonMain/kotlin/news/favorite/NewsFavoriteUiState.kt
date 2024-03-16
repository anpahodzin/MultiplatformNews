package news.favorite

import news.model.News

sealed class NewsFavoriteUiState {

    data object Initial : NewsFavoriteUiState()

    data object Loading : NewsFavoriteUiState()

    data object Error : NewsFavoriteUiState()

    data class Data(
        val favoriteNews: List<News>
    ) : NewsFavoriteUiState()
}