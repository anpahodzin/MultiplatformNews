package news

import cache.Cache
import cache.MemoryCache
import news.model.News

class NewsMemoryCache : Cache<List<News>> by MemoryCache()