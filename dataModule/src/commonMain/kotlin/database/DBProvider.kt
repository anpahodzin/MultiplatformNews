package database

abstract class DBProvider<T>(private val initializer: DatabaseInitializer) {

    private var dao: T? = null

    abstract suspend fun getDao(initializer: DatabaseInitializer): T

    suspend operator fun invoke(): T {
        return dao ?: getDao(initializer).also { dao = it }
    }
}