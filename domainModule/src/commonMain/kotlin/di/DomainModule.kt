package di

import news.favorite.NewsFavoriteViewModel
import news.list.NewsListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::NewsListViewModel)
    factoryOf(::NewsFavoriteViewModel)
}