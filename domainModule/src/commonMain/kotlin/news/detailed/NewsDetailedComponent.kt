package news.detailed

import com.arkivanov.decompose.value.Value
import news.News

interface NewsDetailedComponent {
    val state: Value<News>

    fun onBackPressed()
}