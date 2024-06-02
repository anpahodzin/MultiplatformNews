package news.topheadlines

import com.arkivanov.decompose.ComponentContext
import core.flow.AnyStateFlow
import core.flow.wrapToAny
import core.getOrCreateViewModel
import news.model.News
import news.model.NewsCategory
import org.koin.core.component.KoinComponent

class NewsTopHeadlinesDefaultComponent(
    private val componentContext: ComponentContext,
    private val onNewsSelected: (news: News) -> Unit,
) : NewsTopHeadlinesComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel: NewsTopHeadlinesViewModel = getOrCreateViewModel()

    override val state: AnyStateFlow<NewsTopHeadlinesUiState> = viewModel.state.wrapToAny()

    override fun onNewsSelected(news: News) {
        onNewsSelected.invoke(news)
    }

    override fun onCategorySelected(category: NewsCategory) {
        viewModel.onCategorySelected(category)
    }

    override fun refresh() {
        viewModel.refresh()
    }
}