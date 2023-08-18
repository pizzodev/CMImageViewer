package koin

import api.ApiClient
import api.ApiClientImpl
import org.koin.core.context.startKoin
import org.koin.dsl.module
import repo.RandomImageRepository
import repo.RandomImageRepositoryImpl

var koinInit = false

fun initKoin() {
    if (!koinInit) {
        startKoin {
            modules(
                apiModule(),
                repoModule()
            )
        }
        koinInit = true
    }
}

fun repoModule() = module {
    single<RandomImageRepository> { RandomImageRepositoryImpl() }
}


fun apiModule() = module {
    single<ApiClient> { ApiClientImpl() }
}