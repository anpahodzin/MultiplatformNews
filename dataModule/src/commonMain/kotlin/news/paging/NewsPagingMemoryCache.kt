package news.paging

import cache.Cache
import cache.MemoryCache
import news.model.News
import pagination.PagedData

class NewsPagingMemoryCache : Cache<PagedData<News>> by MemoryCache()