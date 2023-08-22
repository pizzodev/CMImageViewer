import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import api.getAndroidApiClient
import io.ktor.client.*
import ui.LoadAsyncImage
import utils.Platform

actual fun getPlatform(): Platform = Platform.Android
@Composable
actual fun BuildComposeItem(randomImageUrl: String?, imageBitmap: ImageBitmap?) { LoadAsyncImage(randomImageUrl) }

actual fun getApiClient(): HttpClient = getAndroidApiClient()

@Composable fun MainView() = App()

actual fun byteArrayToImage(byteArray: ByteArray): ImageBitmap? { return null; }

