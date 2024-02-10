package main.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import news.model.News
import news.detailed.NewsDetailedDefaultComponent
import news.list.NewsListDefaultComponent

class MainDefaultComponent(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, MainComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.NewsList) },
        handleBackButton = true,
    ) { config: Config, componentContext: ComponentContext ->
        when (config) {
            is Config.NewsList -> MainComponent.Child.NewsList(
                NewsListDefaultComponent(
                    componentContext = componentContext,
                    onNewsSelected = { news -> navigation.push(Config.NewsDetailed(news)) }
                )
            )

            is Config.NewsDetailed -> {
                MainComponent.Child.NewsDetailed(
                    NewsDetailedDefaultComponent(
                        componentContext = componentContext,
                        news = config.news,
                        onBack = { navigation.pop() }
                    )
                )
            }
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object NewsList : Config

        @Serializable
        data class NewsDetailed(val news: News) : Config
    }
}