package news.list

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import news.NewsRepository

class NewsListViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val _state = MutableStateFlow<NewsListUiState>(NewsListUiState.Loading)
    val state = _state.asStateFlow()

    init {
        updateWeather()
    }

    private fun updateWeather() {
        launch {
            _state.tryEmit(NewsListUiState.Loading)
            runCatchingCancellable {
                repository.getNews()
            }.onSuccess {
                _state.tryEmit(NewsListUiState.Data(it))
            }.onFailure {
                _state.tryEmit(NewsListUiState.Error)
            }
        }
    }
}