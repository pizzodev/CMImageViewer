import androidx.compose.runtime.Composable
import api.getAndroidApiClient
import io.ktor.client.*
import ui.LoadAsyncImage

actual fun getPlatformName(): String = "Android"
@Composable
actual fun LoadImageFromUrl(randomImageUrl: String) { LoadAsyncImage(randomImageUrl) }

actual fun getApiClient(): HttpClient = getAndroidApiClient()

@Composable fun MainView() = App()
