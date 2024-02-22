package database

abstract class DBProvider<T>(private val initializer: DatabaseInitializer) {

    var queries: T? = null

    abstract suspend fun getQueries(initializer: DatabaseInitializer): T

    suspend operator fun invoke(): T {
        return queries ?: getQueries(initializer).also { queries = it }
    }
}