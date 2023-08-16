package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import getPlatformName

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {

    //Init view model
    viewModel.initViewModel()

    val state = viewModel.mainScreenState.collectAsState().value

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            viewModel.getPosts()
        }) {
            Text("Fetch posts")
        }

        AnimatedVisibility(
            visible = state.status == MainScreenStatus.loading,
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = state.status == MainScreenStatus.loaded
        ) {
            Text("Hello ${getPlatformName()}");
            Text(state.posts ?: "No data")
        }
    }
}