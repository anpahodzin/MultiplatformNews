package di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin() {
    initKoin {}
}

fun initKoin(declaration: KoinAppDeclaration) {
    startKoin {
        declaration()
        modules(dataModule + platformDataModule() + domainModule)
    }
}