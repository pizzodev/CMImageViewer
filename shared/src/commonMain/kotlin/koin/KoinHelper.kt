package koin

import api.ApiClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

var koinInit = false

fun initKoin() {
    if (!koinInit) {
        startKoin {
            modules(apiModule())
        }
        koinInit = true
    }
}


fun apiModule() = module {

    single { ApiClient() }

    //single<PeopleInSpaceRepositoryInterface> { PeopleInSpaceRepository() }
}