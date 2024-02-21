package database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import kotlinx.coroutines.await
import org.example.kmpnews.MyDatabase

actual class DatabaseDriverFactory {

    actual suspend fun createDriver(): SqlDriver = initSqlDriver(MyDatabase.Schema).await()
}