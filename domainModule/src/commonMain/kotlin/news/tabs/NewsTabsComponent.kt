package news.tabs

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value
import news.everything.NewsEverythingComponent
import news.favorite.NewsFavoriteComponent
import news.topheadlines.NewsTopHeadlinesComponent

interface NewsTabsComponent {
    @OptIn(ExperimentalDecomposeApi::class)
    val childPages: Value<ChildPages<*, TabChild>>

    sealed class TabChild {
        class NewsTopHeadlines(val component: NewsTopHeadlinesComponent) : TabChild()
        class NewsEverything(val component: NewsEverythingComponent) : TabChild()
        class NewsFavorite(val component: NewsFavoriteComponent) : TabChild()
    }

    fun selectPage(index: Int)
}