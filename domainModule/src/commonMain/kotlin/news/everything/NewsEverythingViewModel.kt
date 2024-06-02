package news.everything

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import news.NewsRepository
import news.model.News

class NewsEverythingViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val lastNews = MutableStateFlow(emptyList<News>())
    private val _state = MutableStateFlow<NewsEverythingUiState>(
        NewsEverythingUiState.Empty(searchQuery.value)
    )
    val state = _state.asStateFlow()

    init {
        launch {
            searchQuery.collectLatest {
                if (it.isNotBlank()) {
                    _state.tryEmit(NewsEverythingUiState.Loading(it))
                    delay(500)
                    updateEverythingNews(it)
                }else{
                    _state.tryEmit(NewsEverythingUiState.Empty(it))
                }
            }
        }
    }

    private suspend fun updateEverythingNews(query: String) {
        runCatchingCancellable {
            repository.getEverythingNews(query, 1, 10) //todo hardcoded
        }.onSuccess {
            _state.tryEmit(NewsEverythingUiState.Data(query, it))
        }.onFailure {
            _state.tryEmit(NewsEverythingUiState.Error(query))
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery.tryEmit(query)
    }

    fun refresh() {
        launch {
            val search = searchQuery.value
            _state.tryEmit(NewsEverythingUiState.Loading(search))
            updateEverythingNews(search)
        }
    }
}