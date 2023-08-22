package repo

import androidx.compose.ui.graphics.ImageBitmap
import api.ApiClient
import api.RandomImages
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RandomImageRepository {
    suspend fun getRandomImages(): RandomImages
    suspend fun getImageFromUrl(imageUrl: String): Result<ImageBitmap?>
}

class RandomImageRepositoryImpl: RandomImageRepository, KoinComponent {

    private val apiClient: ApiClient by inject()

    override suspend fun getRandomImages(): RandomImages {
        return apiClient.getRandomImages()
    }

    override suspend fun getImageFromUrl(imageUrl: String): Result<ImageBitmap?> {
        return apiClient.getImageFromUrl(imageUrl)
    }
}