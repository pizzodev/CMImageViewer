package repo

import api.ApiClient
import api.RandomImages
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RandomImageRepository {
    suspend fun getRandomImages(): RandomImages
}

class RandomImageRepositoryImpl: RandomImageRepository, KoinComponent {

    private val apiClient: ApiClient by inject()

    override suspend fun getRandomImages(): RandomImages {
        return apiClient.getRandomImages()
    }
}