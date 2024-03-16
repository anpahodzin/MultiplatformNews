package news.database

import database.DBProvider
import database.DatabaseInitializer
import org.example.kmpnews.data.news.NewsDaoQueries

class NewsDatabase(initializer: DatabaseInitializer) : DBProvider<NewsDaoQueries>(initializer) {

    override suspend fun getDao(initializer: DatabaseInitializer) = initializer().newsDaoQueries
}