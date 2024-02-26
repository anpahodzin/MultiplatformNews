package news.tabs

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import news.favorite.NewsFavoriteComponent
import news.list.NewsListComponent

interface NewsTabsComponent {
    @OptIn(ExperimentalDecomposeApi::class)
    val childPages: Value<ChildPages<*, TabChild>>

    sealed class TabChild {
        class NewsList(val component: NewsListComponent) : TabChild()
        class NewsFavorite(val component: NewsFavoriteComponent) : TabChild()
    }

    fun selectPage(index: Int)
}