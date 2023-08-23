import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.ComposeUIViewController
import api.getDarwinApiClient
import com.seiko.imageloader.Image
import io.ktor.client.*
import ui.LoadAsyncImage
import utils.Platform

actual fun getPlatform(): Platform = Platform.iOS

@Composable
actual fun BuildComposeItem(randomImageUrl: String?, imageBitmap: ImageBitmap?) { LoadAsyncImage(imageBitmap) }

actual fun byteArrayToImage(byteArray: ByteArray): ImageBitmap? {
    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}

actual fun getApiClient(): HttpClient = getDarwinApiClient()

fun MainViewController() = ComposeUIViewController { App() }