package di

import MultiplatformNews.dataModule.BuildConfig
import network.HttpClientProvider
import network.JsonProvider
import news.api.NewsApi
import news.NewsDataRepository
import news.NewsMemoryCache
import news.NewsRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::HttpClientProvider)
    factoryOf(::JsonProvider)
    singleOf(JsonProvider::get)

    single {
        NewsApi(
            get<HttpClientProvider>().get(
                BuildConfig.newsApiUrl,
                mapOf("apiKey" to BuildConfig.newsApiKey)
            )
        )
    }
    singleOf(::NewsDataRepository) bind NewsRepository::class
    singleOf(::NewsMemoryCache)
}