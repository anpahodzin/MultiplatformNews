package news

import cache.Cache
import cache.MemoryCache

class NewsMemoryCache : Cache<List<News>> by MemoryCache()