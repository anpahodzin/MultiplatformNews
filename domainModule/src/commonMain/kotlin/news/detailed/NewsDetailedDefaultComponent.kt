package news.detailed

import com.arkivanov.decompose.ComponentContext
import core.flow.AnyStateFlow
import core.flow.wrapToAny
import core.getOrCreateViewModel
import kotlinx.coroutines.flow.Flow
import news.model.News
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf

class NewsDetailedDefaultComponent(
    private val componentContext: ComponentContext,
    private val news: News,
    private val onBack: () -> Unit,
) : NewsDetailedComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel: NewsDetailedViewModel = getOrCreateViewModel { parametersOf(news) }

    override val state: AnyStateFlow<NewsDetailedUiState> = viewModel.state.wrapToAny()

    override val eventChannel: Flow<NewsDetailedUiEvent> = viewModel.eventChannel

    override fun onBackPressed() {
        onBack.invoke()
    }

    override fun onFavoritePressed() {
        viewModel.onFavoritePressed()
    }
}