package news.detailed

import core.flow.AnyStateFlow
import kotlinx.coroutines.flow.Flow

interface NewsDetailedComponent {
    val state: AnyStateFlow<NewsDetailedUiState>
    val eventChannel: Flow<NewsDetailedUiEvent>

    fun onBackPressed()

    fun onFavoritePressed()
}