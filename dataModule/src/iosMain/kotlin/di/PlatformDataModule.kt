package di

import database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun platformDataModule(): Module = module {
    singleOf(::DatabaseDriverFactory)
}