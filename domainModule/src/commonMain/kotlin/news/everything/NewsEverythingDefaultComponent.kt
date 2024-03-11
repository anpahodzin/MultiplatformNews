package news.everything

import com.arkivanov.decompose.ComponentContext
import core.flow.AnyStateFlow
import core.flow.wrapToAny
import core.getOrCreateViewModel
import news.model.News
import news.model.NewsCategory
import org.koin.core.component.KoinComponent

class NewsEverythingDefaultComponent(
    private val componentContext: ComponentContext,
    private val onNewsSelected: (news: News) -> Unit,
) : NewsEverythingComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel: NewsEverythingViewModel = getOrCreateViewModel()

    override val state: AnyStateFlow<NewsEverythingUiState> = viewModel.state.wrapToAny()

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