package news.favorite

import core.ComponentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import news.NewsRepository

class NewsFavoriteViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val _state =
        MutableStateFlow<NewsFavoriteUiState>(NewsFavoriteUiState.Initial)
    val state = _state.asStateFlow()

    init {
        subscribeFavoriteNews()
    }

    private fun subscribeFavoriteNews() {
        launch {
            _state.tryEmit(NewsFavoriteUiState.Loading)
            repository.flowFavoriteNews()
                .catch { _state.tryEmit(NewsFavoriteUiState.Error) }
                .collectLatest { _state.tryEmit(NewsFavoriteUiState.Data(it)) }
        }
    }
}