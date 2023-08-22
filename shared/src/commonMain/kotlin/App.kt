import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import io.ktor.client.*
import koin.initKoin
import ui.MainScreen
import ui.MainScreenViewModel
import utils.Platform

@Composable
fun App() {

    initKoin()

    MaterialTheme {
        MainScreen(MainScreenViewModel())
    }
}

expect fun getPlatform(): Platform

expect fun getApiClient(): HttpClient

expect fun BuildComposeItem(randomImageUrl: String?, imageBitmap: ImageBitmap?)

expect fun byteArrayToImage(byteArray: ByteArray): ImageBitmap?