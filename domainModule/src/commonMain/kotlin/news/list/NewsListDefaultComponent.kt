package news.list

import com.arkivanov.decompose.ComponentContext
import core.flow.AnyStateFlow
import core.flow.wrapToAny
import core.getOrCreateViewModel
import news.News
import org.koin.core.component.KoinComponent

class NewsListDefaultComponent(
    private val componentContext: ComponentContext,
    private val onNewsSelected: (news: News) -> Unit,
) : NewsListComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel: NewsListViewModel = getOrCreateViewModel()

    override val state: AnyStateFlow<NewsListUiState> = viewModel.state.wrapToAny()

    override fun onNewsSelected(news: News) {
        onNewsSelected.invoke(news)
    }
}