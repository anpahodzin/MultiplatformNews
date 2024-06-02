package news.paging

import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.createPager
import kotlinx.coroutines.flow.Flow
import news.NewsPagingRepository
import news.api.NewsApi
import news.model.News
import news.model.NewsCategory
import news.model.toPagedData
import pagination.Paging3DataSource

class NewsPagingDataRepository(
    private val api: NewsApi,
    private val pagedCache: NewsPagingMemoryCache,
) : NewsPagingRepository {

    override suspend fun getPagingTopHeadlinesNews(
        config: PagingConfig,
        category: NewsCategory
    ): Flow<PagingData<News>> {
        return createPager(
            config = config,
            pagingSourceFactory = {
                Paging3DataSource { page, pageSize ->

                    pagedCache["$category$page"] ?: api
                        .getTopHeadlinesNews(
                            category = category.name,
                            page = page,
                            pageSize = pageSize
                        )
                        .toPagedData()
                        .also { pagedCache["$category$page"] = it }

                }
            }
        ).flow
    }
}