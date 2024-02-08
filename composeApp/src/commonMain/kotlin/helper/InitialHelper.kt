package helper

import di.dataModule
import di.domainModule
import org.koin.core.context.startKoin

fun initCommon() {
    startKoin {
        modules(dataModule, domainModule)
    }
}