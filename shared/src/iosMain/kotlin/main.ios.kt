import androidx.compose.ui.window.ComposeUIViewController
import api.getDarwinApiClient
import io.ktor.client.*
import ui.loadAsyncImageiOS

actual fun getPlatformName(): String = "iOS"

actual fun LoadImageFromUrl(randomImageUrl: String) { loadAsyncImageiOS(randomImageUrl) }

actual fun getApiClient(): HttpClient = getDarwinApiClient()

fun MainViewController() = ComposeUIViewController { App() }