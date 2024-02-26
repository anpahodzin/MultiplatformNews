package news.favorite

import com.arkivanov.decompose.ComponentContext
import core.flow.AnyStateFlow
import core.flow.wrapToAny
import core.getOrCreateViewModel
import news.model.News
import org.koin.core.component.KoinComponent

class NewsFavoriteDefaultComponent(
    private val componentContext: ComponentContext,
    private val onFavoriteNewsSelected: (news: News) -> Unit,
) : NewsFavoriteComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel: NewsFavoriteViewModel = getOrCreateViewModel()

    override val state: AnyStateFlow<NewsFavoriteUiState> = viewModel.state.wrapToAny()

}