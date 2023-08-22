import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.ComposeUIViewController
import api.getDarwinApiClient
import com.seiko.imageloader.Image
import io.ktor.client.*
import utils.Platform
import androidx.compose.foundation.Image as ComposeImage

actual fun getPlatform(): Platform = Platform.iOS

@Composable
actual fun BuildComposeItem(randomImageUrl: String?, imageBitmap: ImageBitmap?) {

    imageBitmap?.let { pic ->

        val bitmapState: ImageBitmap? by remember { mutableStateOf(pic) }

        bitmapState?.let { bitmap ->
            ComposeImage(
                bitmap = bitmap,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                alpha = DefaultAlpha,
                colorFilter = null,
            )
        }

    }
}

actual fun byteArrayToImage(byteArray: ByteArray): ImageBitmap? {
    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}

actual fun getApiClient(): HttpClient = getDarwinApiClient()

fun MainViewController() = ComposeUIViewController { App() }