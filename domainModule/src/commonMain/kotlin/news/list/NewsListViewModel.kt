package news.list

import core.ComponentViewModel
import core.runCatchingCancellable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import news.NewsRepository
import news.model.NewsCategory

class NewsListViewModel(private val repository: NewsRepository) : ComponentViewModel() {

    private val selectedCategories = MutableStateFlow(NewsCategory.Business)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<NewsListUiState> = selectedCategories
        .flatMapLatest(::topHeadlinesNews)
        .stateIn(this, SharingStarted.Eagerly, NewsListUiState.Loading)

    private fun topHeadlinesNews(category: NewsCategory): Flow<NewsListUiState> {
        return flow {
            emit(NewsListUiState.Loading)
            runCatchingCancellable {
                repository.getTopHeadlinesNews(category)
            }.onSuccess {
                emit(NewsListUiState.Data(it, category))
            }.onFailure {
                emit(NewsListUiState.Error)
            }
        }
    }

    fun onCategorySelected(category: NewsCategory) {
        selectedCategories.tryEmit(category)
    }
}