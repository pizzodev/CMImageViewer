package api

import getApiClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

interface ApiClient: KoinComponent {
    val httpClient: HttpClient

    suspend fun getRandomImages(): RandomImages
}
class ApiClientImpl: ApiClient, KoinComponent {
    private val BASE_URL = "https://dog.ceo/api"

    override val httpClient = getApiClient()

    override suspend fun getRandomImages(): RandomImages {
        return httpClient.get("$BASE_URL/breeds/image/random/10").body()
    }
}

@Serializable
data class RandomImages(
    val message: List<String>,
    val status: String
)