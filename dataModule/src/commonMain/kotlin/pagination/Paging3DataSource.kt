package pagination

import app.cash.paging.*

class Paging3DataSource<T : Any>(
    private val initialKey: Int = 1,
    private val getItemsForPage: suspend (page: Int, pageSize: Int) -> PagedData<T>,
) : PagingSource<Int, T>() {

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, T> {
        try {
            val pageNumber = params.key ?: initialKey
            val pageSize = params.loadSize
            val pagedData = getItemsForPage.invoke(pageNumber, pageSize)

            val nextPageNumber =
                if (pageNumber * pageSize >= pagedData.total) null else pageNumber + 1
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null

            return PagingSourceLoadResultPage<Int, T>(
                data = pagedData.data,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber,
                itemsBefore = COUNT_UNDEFINED,
                itemsAfter = pagedData.total - (pageNumber * pageSize)
            ) as PagingSourceLoadResult<Int, T>
        } catch (e: Exception) {
            return PagingSourceLoadResultError<Int, T>(e) as PagingSourceLoadResult<Int, T>
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}