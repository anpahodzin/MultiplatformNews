package database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.example.kmpnews.MyDatabase

actual class DatabaseDriverFactory {

    actual suspend fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
            MyDatabase.Schema.create(it)
        }
    }
}