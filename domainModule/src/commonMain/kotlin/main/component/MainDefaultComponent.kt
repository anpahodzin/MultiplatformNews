package main.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import news.component.NewsListDefaultComponent

class MainDefaultComponent(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, MainComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.NewsList) },
    ) { config: Config, componentContext: ComponentContext ->
        when (config) {
            is Config.NewsList -> {
                MainComponent.Child.NewsList(NewsListDefaultComponent(componentContext))
            }
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object NewsList : Config
    }
}