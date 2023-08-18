import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.ktor.client.*
import koin.initKoin
import ui.MainScreen
import ui.MainScreenViewModel

@Composable
fun App() {

    initKoin()

    MaterialTheme {
        MainScreen(MainScreenViewModel())
    }
}

expect fun getPlatformName(): String

expect fun getApiClient(): HttpClient

expect fun LoadImageFromUrl(randomImageUrl: String)