package database

import database.adapter.DateInstantAdapter
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.example.kmpnews.MyDatabase
import org.example.kmpnews.data.news.NewsEntity

class DatabaseInitializer(
    private val driverFactory: DatabaseDriverFactory
) {

    private var database: MyDatabase? = null
    private val mutex = Mutex()

    // USE .awaitAsList() or .awaitAsOne() or .awaitAsOneOrNull() for get result
    private suspend fun getOrCreateDatabase(): MyDatabase = mutex.withLock {
        return MyDatabase.invoke(
            driver = driverFactory.createDriver(),
            NewsEntityAdapter = NewsEntity.Adapter(DateInstantAdapter())
        ).also {
            database = it
        }
    }

    suspend operator fun invoke(): MyDatabase {
        return database ?: getOrCreateDatabase()
    }
}