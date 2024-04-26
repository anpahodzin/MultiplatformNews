package pagination

data class PagedData<T>(
    var total: Int,
    var data: List<T>
)