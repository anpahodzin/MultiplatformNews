package news.api

import news.model.NewsDtoResponse

interface NewsApi {

    suspend fun getEverythingNews(query: String, page: Int, pageSize: Int): NewsDtoResponse

    suspend fun getTopHeadlinesNews(
        query: String? = null,
        category: String,
        page: Int,
        pageSize: Int
    ): NewsDtoResponse
}