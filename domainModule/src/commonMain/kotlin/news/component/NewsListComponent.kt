package news.component

import core.flow.AnyStateFlow

interface NewsListComponent {
    val state: AnyStateFlow<NewsListUiState>
}