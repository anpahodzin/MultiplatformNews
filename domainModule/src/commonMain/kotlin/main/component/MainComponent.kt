package main.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import news.detailed.NewsDetailedComponent
import news.list.NewsListComponent

interface MainComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class NewsList(val component: NewsListComponent) : Child()
        class NewsDetailed(val component: NewsDetailedComponent) : Child()
    }
}