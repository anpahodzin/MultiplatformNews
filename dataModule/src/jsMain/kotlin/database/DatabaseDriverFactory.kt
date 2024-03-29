package database

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.example.kmpnews.MyDatabase
import org.w3c.dom.Worker

actual class DatabaseDriverFactory {

    actual suspend fun createDriver(): SqlDriver {
        @Suppress("UnsafeCastFromDynamic")
        val driver: SqlDriver = WebWorkerDriver(
            Worker(js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)"""))
        )

        MyDatabase.Schema.awaitCreate(driver)
        return driver
    }
}