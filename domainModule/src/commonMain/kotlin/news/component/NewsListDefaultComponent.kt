package news.component

import com.arkivanov.decompose.ComponentContext
import core.flow.AnyStateFlow
import core.flow.wrapToAny
import core.getOrCreateViewModel
import org.koin.core.component.KoinComponent

class NewsListDefaultComponent(
    private val componentContext: ComponentContext,
) : NewsListComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel: NewsListViewModel = getOrCreateViewModel()

    override val state: AnyStateFlow<NewsListUiState> = viewModel.state.wrapToAny()
}