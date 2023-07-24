import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.MainScreen
import ui.MainScreenViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        MainScreen(MainScreenViewModel())
    }
}

expect fun getPlatformName(): String