package news.tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import news.favorite.NewsFavoriteDefaultComponent
import news.list.NewsListDefaultComponent
import news.model.News

class NewsTabsDefaultComponent(
    componentContext: ComponentContext,
    private val onNewsSelected: (news: News) -> Unit,
) : NewsTabsComponent, ComponentContext by componentContext {

    @OptIn(ExperimentalDecomposeApi::class)
    private val navigationPages = PagesNavigation<TabConfig>()

    @OptIn(ExperimentalDecomposeApi::class)
    override val childPages: Value<ChildPages<*, NewsTabsComponent.TabChild>> = childPages(
        source = navigationPages,
        serializer = TabConfig.serializer(),
        initialPages = {
            Pages(
                listOf(TabConfig.NewsList, TabConfig.NewsFavorite),
                selectedIndex = 0
            )
        },
    ) { config, componentContext ->
        when (config) {
            TabConfig.NewsList -> NewsTabsComponent.TabChild.NewsList(
                NewsListDefaultComponent(
                    componentContext = componentContext,
                    onNewsSelected = onNewsSelected,
                )
            )

            TabConfig.NewsFavorite -> NewsTabsComponent.TabChild.NewsFavorite(
                NewsFavoriteDefaultComponent(
                    componentContext = componentContext,
                    onNewsSelected = onNewsSelected,
                )
            )
        }
    }

    @OptIn(ExperimentalDecomposeApi::class)
    override fun selectPage(index: Int) {
        navigationPages.select(index = index)
    }

    @Serializable
    sealed interface TabConfig {
        @Serializable
        data object NewsList : TabConfig

        @Serializable
        data object NewsFavorite : TabConfig
    }
}