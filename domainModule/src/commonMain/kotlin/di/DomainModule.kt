package di

import news.detailed.NewsDetailedViewModel
import news.everything.NewsEverythingViewModel
import news.favorite.NewsFavoriteViewModel
import news.model.News
import news.topheadlines.NewsTopHeadlinesViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::NewsTopHeadlinesViewModel)
    factoryOf(::NewsEverythingViewModel)
    factoryOf(::NewsFavoriteViewModel)
    factory { (selectedNews: News) -> NewsDetailedViewModel(selectedNews, get()) }
}