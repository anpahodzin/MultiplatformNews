package di

import news.component.NewsListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::NewsListViewModel)
}