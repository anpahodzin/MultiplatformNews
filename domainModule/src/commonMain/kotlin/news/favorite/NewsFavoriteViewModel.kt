package news.favorite

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import news.NewsRepository

class NewsFavoriteViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val _state =
        MutableStateFlow<NewsFavoriteUiState>(NewsFavoriteUiState.Initial)
    val state = _state.asStateFlow()

    init {
        getFavoriteNews()
    }

    private fun getFavoriteNews() {
        launch {
            _state.tryEmit(NewsFavoriteUiState.Loading)
            runCatchingCancellable {
                repository.getFavoriteNews()
            }.onSuccess {
                _state.tryEmit(NewsFavoriteUiState.Data(it))
            }.onFailure {
                _state.tryEmit(NewsFavoriteUiState.Error)
            }
        }
    }
}