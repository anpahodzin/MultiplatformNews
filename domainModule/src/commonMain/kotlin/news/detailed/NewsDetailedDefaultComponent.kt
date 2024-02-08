package news.detailed

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import news.News
import org.koin.core.component.KoinComponent

class NewsDetailedDefaultComponent(
    private val componentContext: ComponentContext,
    private val news: News,
) : NewsDetailedComponent, ComponentContext by componentContext, KoinComponent {

    override val state: Value<News> = MutableValue(news)
}