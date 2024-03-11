package news.everything

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import news.NewsRepository
import news.model.NewsCategory

class NewsEverythingViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val selectedCategory = MutableStateFlow(NewsCategory.Business)

    private val _state =
        MutableStateFlow<NewsEverythingUiState>(NewsEverythingUiState.Initial)
    val state = _state.asStateFlow()

    init {
        launch {
            selectedCategory.collectLatest {
                updateTopHeadlinesNews(category = it)
            }
        }
    }

    private fun updateTopHeadlinesNews(category: NewsCategory) {
        launch {
            _state.tryEmit(NewsEverythingUiState.Loading(category))
            runCatchingCancellable {
                repository.getTopHeadlinesNews(category)
            }.onSuccess {
                _state.tryEmit(NewsEverythingUiState.Data(category, it))
            }.onFailure {
                _state.tryEmit(NewsEverythingUiState.Error(category))
            }
        }
    }

    fun onCategorySelected(category: NewsCategory) {
        selectedCategory.tryEmit(category)
    }

    fun refresh() {
        updateTopHeadlinesNews(selectedCategory.value)
    }
}