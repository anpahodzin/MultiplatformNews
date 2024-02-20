package database

import database.adapter.DateInstantAdapter
import org.example.kmpnews.MyDatabase
import org.example.kmpnews.data.news.NewsEntity

class DatabaseInitializer(
    private val driverFactory: DatabaseDriverFactory
) {

    private var database: MyDatabase? = null

    private suspend fun getOrCreateDatabase(): MyDatabase {
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