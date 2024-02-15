package news.list

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import news.NewsRepository
import news.model.News
import news.model.NewsCategory

class NewsListViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val selectedCategory = MutableStateFlow(NewsCategory.Business)
    private val newsList = MutableStateFlow<List<News>?>(null)

    private val _state =
        MutableStateFlow<NewsListUiState>(NewsListUiState.Initial)
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
            _state.tryEmit(NewsListUiState.Loading(category))
            runCatchingCancellable {
                repository.getTopHeadlinesNews(category)
            }.onSuccess {
                newsList.tryEmit(it)
                _state.tryEmit(NewsListUiState.Data(category, it))
            }.onFailure {
                _state.tryEmit(NewsListUiState.Error(category))
            }
        }
    }

    fun onCategorySelected(category: NewsCategory) {
        selectedCategory.tryEmit(category)
    }
}