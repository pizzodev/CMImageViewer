package api

import androidx.compose.ui.graphics.ImageBitmap
import byteArrayToImage
import getApiClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

interface ApiClient: KoinComponent {
    val httpClient: HttpClient

    suspend fun getRandomImages(): RandomImages

    suspend fun getImageFromUrl(imageUrl: String): Result<ImageBitmap?>
}
class ApiClientImpl: ApiClient, KoinComponent {

    private val BASE_URL = "https://dog.ceo/api"

    override val httpClient = getApiClient()

    override suspend fun getRandomImages(): RandomImages {
        return httpClient.get("$BASE_URL/breeds/image/random/10").body()
    }

    override suspend fun getImageFromUrl(imageUrl: String): Result<ImageBitmap?> {
        return try {
            val imageBytes = httpClient.get(imageUrl).body<ByteArray>()
            Result.success(byteArrayToImage(imageBytes))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

@Serializable
data class RandomImages(
    val message: List<String>,
    var photos: List<ImageBitmap>? = listOf(),
    val status: String
)