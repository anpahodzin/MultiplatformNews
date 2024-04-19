package di

import MultiplatformNews.dataModule.BuildConfig
import database.DatabaseInitializer
import network.HttpClientProvider
import network.JsonProvider
import network.rootUrl
import news.NewsDataRepository
import news.NewsMemoryCache
import news.NewsRepository
import news.api.NewsApi
import news.api.NewsMockApi
import news.api.NewsRemoteApi
import news.database.NewsDatabase
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


    //News Feature
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
}

fun Scope.newsHttpClient() = get<HttpClientProvider>().get {
    rootUrl(BuildConfig.newsApiUrl)
    url { parameters.append("apiKey", BuildConfig.newsApiKey) }
}