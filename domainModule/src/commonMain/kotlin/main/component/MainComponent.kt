package main.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import news.detailed.NewsDetailedComponent
import news.tabs.NewsTabsComponent

interface MainComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class NewsTabs(val component: NewsTabsComponent) : Child()
        class NewsDetailed(val component: NewsDetailedComponent) : Child()
    }
}