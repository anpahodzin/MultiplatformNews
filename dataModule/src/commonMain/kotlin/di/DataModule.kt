package di

import MultiplatformNews.dataModule.BuildConfig
import database.DatabaseInitializer
import network.HttpClientProvider
import network.JsonProvider
import network.rootUrl
import news.*
import news.NewsDataRepository
import news.api.NewsApi
import news.api.NewsMockApi
import news.api.NewsRemoteApi
import news.database.NewsDatabase
import news.paging.NewsPagingDataRepository
import news.paging.NewsPagingMemoryCache
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    // Network
    singleOf(::HttpClientProvider)
    singleOf(JsonProvider()::get)
    // Database
    singleOf(::DatabaseInitializer)


    //News
    single {
        if (BuildConfig.newsApiKey.isNotBlank()) {
            NewsRemoteApi(newsHttpClient())
        } else {
            NewsMockApi(get())
        }
    } bind NewsApi::class
    singleOf(::NewsDatabase)
    singleOf(::NewsDataRepository) bind NewsRepository::class
    singleOf(::NewsMemoryCache)
    //News Pagination
    singleOf(::NewsPagingDataRepository) bind NewsPagingRepository::class
    singleOf(::NewsPagingMemoryCache)
}

fun Scope.newsHttpClient() = get<HttpClientProvider>().get {
    rootUrl(BuildConfig.newsApiUrl)
    url { parameters.append("apiKey", BuildConfig.newsApiKey) }
}