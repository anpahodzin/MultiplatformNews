package news.favorite

import core.flow.AnyStateFlow

interface NewsFavoriteComponent {
    val state: AnyStateFlow<NewsFavoriteUiState>
}