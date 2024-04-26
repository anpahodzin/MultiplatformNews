package news.topheadlines

import app.cash.paging.createPagingConfig
import core.ComponentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import news.NewsPagingRepository
import news.model.NewsCategory

class NewsTopHeadlinesViewModel(private val repository: NewsPagingRepository) :
    ComponentViewModel() {

    private val selectedCategory = MutableStateFlow(NewsCategory.Business)

    private val _state =
        MutableStateFlow(NewsTopHeadlinesUiState(NewsCategory.Business, emptyFlow()))
    val state = _state.asStateFlow()

    init {
        launch {
            selectedCategory.collectLatest { category ->
                val pagingDataFlow = repository.getPagingTopHeadlinesNews(
                    config = createPagingConfig(PAGE_SIZE),
                    category = category
                )
                _state.tryEmit(NewsTopHeadlinesUiState(category, pagingDataFlow))
            }
        }
    }

    fun onCategorySelected(category: NewsCategory) {
        selectedCategory.tryEmit(category)
    }

    fun refresh() {
    }

    companion object{
        private const val PAGE_SIZE = 10
    }
}