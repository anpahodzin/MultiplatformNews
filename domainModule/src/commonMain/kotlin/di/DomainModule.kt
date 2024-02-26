package di

import news.detailed.NewsDetailedViewModel
import news.favorite.NewsFavoriteViewModel
import news.list.NewsListViewModel
import news.model.News
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::NewsListViewModel)
    factoryOf(::NewsFavoriteViewModel)
    factory { (selectedNews: News) -> NewsDetailedViewModel(selectedNews, get()) }
}